package com.origami.Utils;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class RowsAndColumns {
    
    public int getRowCount(XSSFSheet sheet) {
        int rowCount = 0;
        Iterator<Row> rIterator = sheet.rowIterator();
        
        while(rIterator.hasNext()){
            Row r = rIterator.next();
            if(IsBlank.check(r)) break;
            rowCount++;
        }
        return rowCount;
    }
    public int getColumnCount(XSSFSheet sheet, int _RRI) {
        int colCount = 0;
        XSSFRow row = sheet.getRow(_RRI);
        Iterator<org.apache.poi.ss.usermodel.Cell> cIterator = row.cellIterator();

        while(cIterator.hasNext()){
            if(IsBlank.check(cIterator.next())){
                break;
            }
            colCount++;
        }
        return colCount;
    }
}
