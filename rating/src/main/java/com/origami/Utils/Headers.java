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
        ArrayList<Integer> selectedIndexes = new ArrayList<Integer>();

        while(true){
            Object value = formatter.formatCellValue(sheet.getRow(_RRI).getCell(i));
            if(Arrays.asList(columns).contains(value)){
                selectedIndexes.add(i);
                Collections.sort(selectedIndexes);
            }
            if(IsBlank.check(sheet.getRow(_RRI).getCell(i+1))){
                break;
            }
            i++;
        }
        System.out.println("Selected columns : "+ selectedIndexes);
        System.out.println();
        return selectedIndexes;
    }

    // public ArrayList<Integer> getIndexes(String[] columns, XSSFSheet sheet, Integer[] _RRIs) {
    //     return null;

    // }
 
}
