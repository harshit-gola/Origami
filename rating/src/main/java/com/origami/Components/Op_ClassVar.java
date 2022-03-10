package com.origami.Components;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.origami.Constants;
import com.origami.Interfaces.ASA_test.JobRates;
import com.origami.Utils.Headers;
import com.origami.Utils.IsBlank;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Op_ClassVar {
    String excelPath = Constants.FILEPATH + Constants.FILE_PRODUCT_DEFINITION + Constants.FILETYPE;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    String[] column = null;
    int _RRI = 0;
    int _CVI = 0;
    DataFormatter formatter = new DataFormatter();

    public Op_ClassVar(int sheetNo, String sheetName, String[] columnToExtract, int _RRI, int _CVI) throws IOException{
        this.workbook = new XSSFWorkbook(excelPath);
        if(sheetName.equals("") || sheetName.isBlank() || sheetName.isEmpty())
            this.sheet = workbook.getSheetAt(sheetNo);
        else
            this.sheet = workbook.getSheet(sheetName);

        this.column = columnToExtract;
        this._RRI = _RRI;
        this._CVI = _CVI;
        // this.cellDataList = classCodes;
    }    

    public Boolean setClassVariable(ArrayList<JobRates> classCodes) throws IOException{
        System.out.println("Setting up class variables ...");
        Headers header = new Headers();
        ArrayList<Integer> headers = header.getIndexes(this.column, this.sheet, this._RRI);
        setCellData(headers,classCodes);
        this.workbook.close();
        return true;
    }
    
    private void setCellData(ArrayList<Integer> headers, ArrayList<JobRates> classCodes){
        Iterator<Row> rIterator = this.sheet.rowIterator();

        int i = 0;
        
        while(rIterator.hasNext()){
            while(i != this._RRI+1){
                rIterator.next();
                i++;
            }
            Row row = rIterator.next();
            Iterator<Integer> header = headers.iterator();

            if(!IsBlank.check(row.getCell(this._CVI))){
                setClassVariables(row, header, classCodes);
            } else {
                break;  
            }
        }
    }

    private void setClassVariables(Row row, Iterator<Integer> header, ArrayList<JobRates> classCodes){
        int j = -1;
        boolean isPresent = false;
        int index = 0;

        // JobRates cluster = new JobRates();
        while(header.hasNext()){
            Object val = formatter.formatCellValue(row.getCell(header.next()));
            j++;

            if(!isPresent){
                for(int z = 0; z < classCodes.size(); z++){
                    if(classCodes.get(z).getJobClassification().equalsIgnoreCase((String) val) && j == 0){
                        isPresent = true;
                        index = z;
                        break;
                    }
                }
            }

            if(isPresent){
                switch (j) {
                    case 0:
                        break;
                        case 1:
                        classCodes.get(index).setProgram((String) val);
                        break;
                    case 2:
                        classCodes.get(index).setDiseaseLoad((String) val);
                        break;
                    case 3:
                        classCodes.get(index).setContractingClass((String) val);
                        break;
                    default:
                        break;
                }
            } else {

            }
        }
    }
}
