package com.origami.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class Headers {

    DataFormatter formatter = new DataFormatter();

    public ArrayList<Integer> getIndexes(String[] columns, XSSFSheet sheet, int _RRI) {

        int i = 0;
        // RowsAndColumns totalRows = new RowsAndColumns();
        RowsAndColumns instance = new RowsAndColumns();
        ArrayList<Integer> selectedIndexes = new ArrayList<Integer>();

        int totalColumns = instance.getColumnCount(sheet, _RRI);
        System.out.println("total numbers of columns are: "+ totalColumns);
        
        while(i != totalColumns){
            Object value = formatter.formatCellValue(sheet.getRow(_RRI).getCell(i));
            if(Arrays.asList(columns).contains(value)){
                selectedIndexes.add(i);
                Collections.sort(selectedIndexes);
            }
            i++;
        }
        System.out.println("Selected columns are : "+ selectedIndexes);
        return selectedIndexes;
    }
}
