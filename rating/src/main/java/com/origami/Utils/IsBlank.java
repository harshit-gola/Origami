package com.origami.Utils;

import com.origami.Constants;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

public class IsBlank {

    static DataFormatter formatter = new DataFormatter();
    static int i = 0;
    
    public static Boolean check(Cell c) {
        if(c == null || c.getCellType() == org.apache.poi.ss.usermodel.CellType.BLANK)
            return true;
        else
            return false;
    }

    public static Boolean check(Row r){
        if(IsBlank.check(r.getCell(0)) && 
        IsBlank.check(r.getCell(1)) && 
        IsBlank.check(r.getCell(2)) && 
        IsBlank.check(r.getCell(3)) && 
        IsBlank.check(r.getCell(4)) && 
        IsBlank.check(r.getCell(5)) && 
        IsBlank.check(r.getCell(6))) return true;
        else return false;
    }

    public static Boolean isZeroRow(Row r){
        i++;
            Object val = formatter.formatCellValue(r.getCell(Constants.indexesForNCCIRates.get(0)));
            if(val.toString().equals("0") || val.toString().equals("0.0")) 
                return true;
            else 
                return false;
    }

    public static Boolean check(String sheetName){
        if(sheetName.equals("") || sheetName.isBlank() || sheetName.isEmpty()) return true;
        else return false;
    }
}
