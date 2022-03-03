package com.origami.Components;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.origami.Constants;
import com.origami.Interfaces.ASA_test.EmpLiaLimit;
import com.origami.Utils.Headers;
import com.origami.Utils.IsBlank;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Op_EmpLiability {
    String excelPath = Constants.FILEPATH + Constants.FILE_PRODUCT_DEFINITION + Constants.FILETYPE;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    String[] column = null;
    int _RRI = 0;
    int _CVI = 0;
    ArrayList<EmpLiaLimit> cellDataList = new ArrayList<EmpLiaLimit>();
    DataFormatter formatter = new DataFormatter();


    public Op_EmpLiability(int sheetNo, String sheetName, String[] columnToExtract, int _RRI, int _CVI) throws IOException{
        this.workbook = new XSSFWorkbook(excelPath);
        if(sheetName.equals("") || sheetName.isBlank() || sheetName.isEmpty())
            this.sheet = workbook.getSheetAt(sheetNo);
        else
            this.sheet = workbook.getSheet(sheetName);

        this.column = columnToExtract;
        this._RRI = _RRI;
        this._CVI = _CVI;
    }

    public ArrayList<EmpLiaLimit> getLimits() throws IOException{
        Headers header = new Headers();
        ArrayList<Integer> headers = header.getIndexes(this.column, this.sheet, this._RRI);
        if(setCellData(headers)){
            this.workbook.close();
            return cellDataList;
        }
        this.workbook.close();
        return null;
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
                cellDataList.add(getEmpLiaLimits(row, header));    
            } else {
                break;  
            }
        }
        return true;
    }

    private EmpLiaLimit getEmpLiaLimits(Row row, Iterator<Integer> header){
        EmpLiaLimit cluster = new EmpLiaLimit();
        int j = -1;
        while(header.hasNext()){
            Object val = formatter.formatCellValue(row.getCell(header.next()));
            j++;

            switch (j) {
                case 0:
                    cluster.setBodyInjuryByAccident((String) val);
                    break;
                case 1:
                    cluster.setBodyInjuryByDiseasePL((String) val);
                    break;
                case 2:
                    cluster.setBodyInjuryByDiseaseEE((String) val);
                    break;
                case 3:
                    cluster.setRate((String) val);
                    break;
                case 4:
                    cluster.setMinPremium((String) val);
                    break;
                case 5:
                    cluster.setStatCode((String) val);
                    break;
                default:
                    break;
            }
        }
        return cluster;
    }
}
