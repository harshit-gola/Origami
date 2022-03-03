package com.origami.Utils;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class RowsAndColumns {
    
    public int getRowCount(XSSFSheet sheet, int _CVI) {
        int rowCount = 0;
        
        Iterator<Row> rIterator = sheet.rowIterator();
        IsBlank isBlank = new IsBlank();
        
        while(rIterator.hasNext()){
            if(isBlank.check(rIterator.next().getCell(_CVI))){
                break;
            }
            rowCount++;
        }
        return rowCount;
    }
    public int getColumnCount(XSSFSheet sheet, int _RRI) {
        IsBlank isBlank = new IsBlank();
        int colCount = 0;
        XSSFRow row = sheet.getRow(_RRI);
        Iterator<org.apache.poi.ss.usermodel.Cell> cIterator = row.cellIterator();

        while(cIterator.hasNext()){
            if(isBlank.check(cIterator.next())){
                break;
            }
            colCount++;
        }
        return colCount;
    }
}
