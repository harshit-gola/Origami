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

public class ExtractStateValues {
    
    String excelPath = Constants.FILEPATH + Constants.FILE_COMPANY_CHART + Constants.FILETYPE;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    String[] column = null;
    int _RRI = 0;
    int _CVI = 0;
    ArrayList<JobRates> dCellDataList = new ArrayList<JobRates>();
    DataFormatter formatter = new DataFormatter();


    public ExtractStateValues(int sheetNo, String sheetName, String[] columnToExtract, int _RRI, int _CVI) throws IOException{

        this.workbook = new XSSFWorkbook(excelPath);
        if(sheetName.equals("") || sheetName.isBlank() || sheetName.isEmpty())
            this.sheet = workbook.getSheetAt(sheetNo);
        else
            this.sheet = workbook.getSheet(sheetName);

        this.column = columnToExtract;
        this._RRI = _RRI;
        this._CVI = _CVI;
    }

    public void run() throws IOException{
        Headers header = new Headers();
        ArrayList<Integer> headers = header.getIndexes(this.column, this.sheet, this._RRI);
        this.workbook.close();
    }

    private Boolean setCellData(ArrayList<Integer> headers){
        Iterator<Row> rIterator = this.sheet.rowIterator();
        IsBlank isBlank = new IsBlank();

        boolean i = true;
        
        while(rIterator.hasNext()){
            while(i){
                rIterator.next();
                i = false;
            }
            Row row = rIterator.next();
            Iterator<Integer> header = headers.iterator();

            if(!isBlank.check(row.getCell(this._CVI))){
                dCellDataList.add(getASATestClassTableVal(row, header));    
            } else {
                break;  
            }
        }
        return true;
    }

    private JobRates getASATestClassTableVal(Row row, Iterator<Integer> header){
        JobRates cluster = new JobRates();
        while(header.hasNext()){
            Object val = formatter.formatCellValue(row.getCell(header.next()));
            cluster.setJobClassification((String) val);
        }
        return cluster;
    }
}

