package com.origami.Utils;

import org.apache.poi.ss.usermodel.Cell;

public class IsBlank {
    
    public Boolean check(Cell c) {
        if(c == null || c.getCellType() == org.apache.poi.ss.usermodel.CellType.BLANK)
            return true;
        else
            return false;
    }
}
