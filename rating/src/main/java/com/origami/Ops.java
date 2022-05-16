package com.origami;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.origami.Interfaces.ASA_test.JobRates;
import com.origami.Utils.Headers;
import com.origami.Utils.IsBlank;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Ops {

    String excelPath;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    Iterator<Row> rIterator;
    List<Integer> headers;
    int _RRI = 0;
    int _CVI = 0;
    int stateIndex = 0;
    private Boolean useLatestRates = false;
    DataFormatter formatter = new DataFormatter();
    private Headers header = new Headers();    
    private Map<Integer, List<String>> data = new LinkedHashMap<Integer, List<String>>();
    ArrayList<JobRates> cluster = new ArrayList<>();

    public Ops(int sheetNo, String sheetName, int _RRI, String excelPath){
        this.excelPath = excelPath;
        try {
            this.workbook = new XSSFWorkbook(this.excelPath);
        } catch (IOException e) {
            System.out.println("Exception while opening the workbook");
            e.printStackTrace();
        }
        if(IsBlank.check(sheetName))
            this.sheet = workbook.getSheetAt(sheetNo);
        else
            this.sheet = workbook.getSheet(sheetName);
        this._RRI = _RRI;
        this.rIterator = this.sheet.rowIterator();
    } 

    public void useLatestRates(){
        this.useLatestRates = true;
    }

    public void getColumnIndexesFromExcelAndSet(Map<String, String> jobClassMapValues){
        if(this.headers != null) this.headers.clear();
        this.headers = header.getIndexes(jobClassMapValues.keySet(), this.sheet, this._RRI);
    }

    public void getColumnIndexesFromExcelAndSet(List<Integer> headers){
        this.headers = headers;
    }

    public void getFromExcelAndSet(){
        if(this.data != null) this.data.clear();
        int i = 0;
        int rowIndex = 0;
        try {
            while(this.rIterator.hasNext()){
                while(i != this._RRI){                                                     // Move cursor to the index from where reading begins
                    this.rIterator.next();      
                    i++;
                }
                Row row = rIterator.next();
                Iterator<Integer> pointer = this.headers.iterator();
                if(this.useLatestRates){                                                    // For NCCI rates (latest rates)
                    if(!IsBlank.check(row) && !IsBlank.isZeroRow(row)){
                        ArrayList<String> rowData = new ArrayList<String>();
                        while(pointer.hasNext()){
                            Object val = formatter.formatCellValue(row.getCell(pointer.next()));
                            rowData.add(appendZeros((String) val));
                        }
                        this.data.put(rowIndex++, rowData);
                    } else break;
                } else {
                    if(!IsBlank.check(row)){
                        ArrayList<String> rowData = new ArrayList<String>();
                        while(pointer.hasNext()){
                            Object val = formatter.formatCellValue(row.getCell(pointer.next()));
                            rowData.add((String) val);
                        }
                        this.data.put(rowIndex++, rowData);
                    } else break; 
                }
            }
        } catch (Exception e) {
            System.err.println("Exception while getting data from excel");
            this.data = Collections.emptyMap();
            e.printStackTrace();
        }
    }

    public Map<Integer, List<String>> getData() {
        return this.data;
    }

    public void filterWith(Map<Integer, List<String>> firstList){
        Map<Integer, List<String>> dList = filterData(firstList,this.data);
        this.data = dList;
    }

    public static Map<Integer, List<String>> filterData(Map<Integer, List<String>> filteredValues, Map<Integer, List<String>> list2Filter){
        Map<Integer, List<String>> filteredData = new LinkedHashMap<Integer, List<String>>();
        int k =0;
        try {            
            for(int i = 0; i < filteredValues.size(); i++){
                if(i != 0){
                    for(int j = 0; j < list2Filter.size(); j++){
                        if(j != 0){
                            if(list2Filter.get(j).contains(filteredValues.get(i).get(0))){
                                filteredData.put(k++, list2Filter.get(j));
                            }
                        }
                    }
                } else {
                    filteredData.put(k++, list2Filter.get(i));
                }
            }
            return filteredData;
        } catch (Exception e) {
            System.err.println("Exception while filtering the data");
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }

    public void mapData(Map<Integer, List<String>> listToAppend){
        Map<Integer, List<String>> dList = mapData(this.data, listToAppend);
        this.data = dList; 
    }

    public static Map<Integer, List<String>> mapData(Map<Integer, List<String>> dList1, Map<Integer, List<String>> dList2){    //All values from list1 will retain and first column from scnd list will remove
        Map<Integer, List<String>> mappedList = new LinkedHashMap<Integer, List<String>>();                                   
        int k =0;                                                                                                           
        try{                                                                                                                
            for(int i = 0; i < dList1.size(); i++){
                boolean doContains = false;
                if(i != 0){
                    for(int j = 0; j < dList2.size(); j++){
                        if(j != 0){
                            if(dList1.get(i).contains(dList2.get(j).get(0))){
                                doContains = true;
                                List<String> row = new ArrayList<>();
                                row.addAll(dList1.get(i));
                                for(int s = 0 ; s < dList2.get(j).size() ; s++){
                                    if(s != 0) row.add(dList2.get(j).get(s));
                                }
                                mappedList.put(k++, row);
                            }
                        }
                    }
                    if(!doContains) {
                        List<String> row = new ArrayList<>();
                        row.addAll(dList1.get(i));
                        for(int s = 0 ; s < dList2.get(0).size() ; s++){
                            if(s != 0) row.add("");
                        }
                        mappedList.put(k++, row);
                    }
                } else {
                    List<String> firstRow = new ArrayList<>();
                    firstRow.addAll(dList1.get(i));
                    for(int s = 0 ; s < dList2.get(i).size() ; s++){
                        if(s != 0) firstRow.add(dList2.get(i).get(s));
                    }
                    mappedList.put(k++, firstRow);
                }
            }
            return mappedList;
            
        } catch (Exception e) {
            System.err.println("Exception while mapping the data");
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }

    public static Map<Integer, List<String>> mapData(Map<Integer, List<String>> dList1, Map<Integer, List<String>> dList2, Boolean appendBelow){
        int i = 0;
        int k = 0;
        Map<Integer, List<String>> mappedList = new LinkedHashMap<Integer, List<String>>();
        if(appendBelow){
            for (List<String> l1 : dList1.values()) {
                mappedList.put(i++, l1);
            }
            for (List<String> l2 : dList2.values()) {
                if(k != 0) mappedList.put(i++, l2);
                k++;
            }
            return mappedList;
        } else return mapData(dList1, dList2);
    }

    // public void setStateIndex(){
    //     this.stateIndex = header.getStateIndexFromRow(this.sheet, this._RRI-1);
    // }

    public static void writeToExcel(String[] dT, Map<String, String> col, Map<Integer, List<String>> data, String fileName){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sprSheet = workbook.createSheet("Upload");
        XSSFRow row;
        for(int i=0; i<= data.size(); i++){
            row = sprSheet.createRow(i);
            int cellId = 0;
            if(i == 0){
                for (String cellValue : dT) {
                    Cell c = row.createCell(cellId++);
                    c.setCellValue((String) cellValue);
                }
            } else if(i ==1){
                for (String cellValue : col.values()) {
                    Cell c = row.createCell(cellId++);
                    c.setCellValue((String) cellValue);
                }
            } else {
                // Object[] keys = col.keySet().toArray();
                // for(int z = 0; z < keys.length; z++){
                //     Cell c = row.createCell(cellId++);
                //     String keyValue = keys[z].toString();
                //     int index = data.get(0).indexOf(keyValue);
                //     if(index == -1 && !data.get(0).stream().anyMatch(s -> s.toString().toLowerCase().contains(keyValue.toLowerCase()))){
                //     } else{
                //     }
                // }
                for(String sKey : col.keySet()){
                    Cell c = row.createCell(cellId++);
                    // data.get(0).stream().forEach(s -> System.out.println(s.substring(0, sKey.length())));
                    int index = data.get(0).indexOf(sKey);
                    if(index != -1){
                        if(sKey == "Rate") c.setCellValue(rateCalculation(data.get(i-1).get(index)));
                        else c.setCellValue(data.get(i-1).get(index));
                    }
                    else
                        c.setCellValue(preDefinedValues(sKey));
                }
            }
        }
        try {
            FileOutputStream out = new FileOutputStream(new File("C:/Users/hagola/Documents/Origami/rating/src/data/States/Output/"+ Constants.CURRENT_STATE + fileName + ".xlsx"));
            workbook.write(out);
            System.out.println(fileName + " Excel Generated...");
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeToExcel(Map<Integer, List<String>> data, String fileName){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sprSheet = workbook.createSheet("Upload");
        XSSFRow row;
        for(int i=0; i< data.size(); i++){
            row = sprSheet.createRow(i);
            int cellId = 0;
            for (String s : data.get(i)) {
                Cell c = row.createCell(cellId++);
                if(s.equalsIgnoreCase("") || s.equalsIgnoreCase("N/A") || s.equalsIgnoreCase("NA")) c.setCellValue(0);
                else c.setCellValue(s);
            }
        }
        try {
            FileOutputStream out = new FileOutputStream(new File("C:/Users/hagola/Documents/Origami/rating/src/data/States/Output/"+ fileName + ".xlsx"));
            workbook.write(out);
            System.out.println(fileName+ " Excel Generated...");
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String preDefinedValues(String sKey){
        if(sKey == "Rating Tier") return Constants.RATING_TIER;
        if(sKey == "Loss Cost Multiplier") return Constants.LCM;
        // if(sKey == "Per Claim") return Constants.PER_CLAIM;
        // if(sKey == "Per Occurrence") return Constants.PER_OCCURRENCE;
        return null;
    }

    private static Double rateCalculation(String rate){
        double val = 0;
        String newRate = rate; 
        if(rate.contains("%")){
            newRate = rate.substring(0, rate.indexOf("%"));
        }
        if(newRate != null) {
            val = Double.parseDouble((String) newRate);
            val = val/100; 
        }
        return val;
    }

    private static String appendZeros(String val){
        String modifiedString = "";
        for(int i = val.length(); i< 4; i++){
            modifiedString = modifiedString + '0';
        }
        modifiedString = modifiedString + val;
        return modifiedString;
    }
}