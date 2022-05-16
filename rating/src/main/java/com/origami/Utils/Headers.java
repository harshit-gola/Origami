package com.origami.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class Headers {

    DataFormatter formatter = new DataFormatter();

    public List<Integer> getIndexes(Set<String> set, XSSFSheet sheet, int _RRI) {
        try {
            int i = 0;
            HashSet<Integer> selectedIndexes = new HashSet<Integer>();
            // HashSet<Integer> lowerCaseSet = set.stream().map(t -> t.toLowerCase()).collect(Collectors.toSet());
            while(true){
                Object value = formatter.formatCellValue(sheet.getRow(_RRI).getCell(i));
                // Object value1 = formatter.formatCellValue(sheet.getRow(_RRI).getCell(i+1));
                // boolean match = (set.stream().anyMatch(t -> value.toString().toLowerCase().contains(t.toLowerCase())) ||
                //                 set.stream().anyMatch(t -> t.toLowerCase().contains(value.toString().toLowerCase()))) && 
                //                 !IsBlank.check(value.toString());
                if(set.contains(value)){
                    // if(value.toString().toLowerCase().equals(value1.toString().toLowerCase()) && isRightState(sheet,_RRI,i))
                    //     selectedIndexes.add(i);
                    // else if(isRightState(sheet,_RRI,i+1)){
                    //     selectedIndexes.add(i+1);
                    //     i++;
                    // }
                    // else
                        selectedIndexes.add(i);
                }
                if(IsBlank.check(sheet.getRow(_RRI).getCell(i+1)) && IsBlank.check(sheet.getRow(_RRI).getCell(i+2))){
                    break;
                }
                i++;
            }
            List<Integer> temp = new ArrayList<>(selectedIndexes);
            Collections.sort(temp);
            System.out.println("Selected columns : "+ temp);
            System.out.println();
            return temp;
          
        } catch (Exception e) {
            System.err.println("Exception while getting indexes for columns");
            e.printStackTrace();
            return Arrays.asList();
        }
    }

    // private Boolean isRightState(XSSFSheet sheet, int _RRI, int i) {
    //     Object value = formatter.formatCellValue(sheet.getRow(_RRI-1).getCell(i));
    //     if(value.toString().equalsIgnoreCase(Constants.STATE)) return true;
    //     else return false;
    // }

    // public int getStateIndexFromRow(XSSFSheet sheet, int _RRI){
    //     int i = 0;
    //     while(true){
    //         Object value = formatter.formatCellValue(sheet.getRow(_RRI).getCell(i));
    //         if(Constants.STATE.equals(value.toString())){
    //             return i;
    //         }
    //         if(IsBlank.check(sheet.getRow(_RRI).getCell(i+1)) && IsBlank.check(sheet.getRow(_RRI).getCell(i+2))){
    //             break;
    //         }
    //         i++;
    //     }
    //     return 0;  
    // }
 
}
