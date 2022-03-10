package com.origami.Utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class IsBlank {
    
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
        IsBlank.check(r.getCell(4))) return true;
        else return false;
    }
}
