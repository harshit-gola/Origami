package com.origami.Components;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import com.origami.Constants;
import com.origami.Interfaces.ASA_test.Coinsurance;
import com.origami.Interfaces.ASA_test.Deductibles;
import com.origami.Interfaces.ASA_test.JobRates;
import com.origami.Utils.Headers;
import com.origami.Utils.IsBlank;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Op_Deductibles {
    String excelPath = Constants.FILEPATH + Constants.FILE_CLASS_TABLE + Constants.FILETYPE;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    String[] column = null;
    int _RRI = 0;
    int _CVI = 0;
    ArrayList<JobRates> dCellDataList = new ArrayList<JobRates>();
    DataFormatter formatter = new DataFormatter();


    public Op_Deductibles(int sheetNo, String sheetName, String[] columnToExtract, int _RRI, int _CVI) throws IOException{
        this.workbook = new XSSFWorkbook(excelPath);
        if(sheetName.equals("") || sheetName.isBlank() || sheetName.isEmpty())
            this.sheet = workbook.getSheetAt(sheetNo);
        else
            this.sheet = workbook.getSheet(sheetName);
        this.column = columnToExtract;
        this._RRI = _RRI;
        this._CVI = _CVI;
    }

    private void run() {
        Headers header = new Headers();
        ArrayList<Integer> headers = header.getIndexes(this.column, this.sheet, this._RRI+1);
        ArrayList<Coinsurance> co = new ArrayList<>();
        ArrayList<Deductibles> de = new ArrayList<>();
        switch (typeOfTable()) {
            case "DEDUCTIBLE":
                setDeductible(headers);
                break;
            case "COINSURANCE":
                
                break;
            default:
                break;
        }
    }

    private void setDeductible(ArrayList<Integer> headers) {
        boolean i = true;
        int j = -1;
        Coinsurance co = new Coinsurance();
        Deductibles de = new Deductibles();
        Iterator<Row> rIterator = this.sheet.rowIterator();
        while(rIterator.hasNext()){
            while(i){
                rIterator.next();
                rIterator.next();
                i = false;
            }
            Row r = rIterator.next();
            for (Integer head : headers) {
                Object val = formatter.formatCellValue(r.getCell(head));
                j++;

                switch (j) {
                    case 0:
                        co.setType((String) val);
                        break;
                    case 1:
                        co.setHazardGrp((String) val);
                        break;
                    case 2:
                        co.setAmount((String) val);
                        break;
                    case 3:
                        co.setPercent((String) val);
                        break;
                    case 4:
                        de.setGroup(co);
                        de.setBasisOfCalc((String) val);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private String typeOfTable(){
        String type = "DEDUCTIBLE";
        String[] coinsuranceKeywords = {"Coinsurance", "With Coinsurance"};
        String[] deductibleKeywords = {"Without Coinsurance"};
        int i = 0;
        int j = 0;
        while(true){
            Object value = formatter.formatCellValue(sheet.getRow(_RRI).getCell(i));
            String stringValue = value.toString().toLowerCase();
            while(j != coinsuranceKeywords.length){
                if(stringValue.indexOf(coinsuranceKeywords[j].toLowerCase()) != -1 && 
                    stringValue.indexOf(deductibleKeywords[0]) == -1){
                    type = "COINSURANCE";
                    break;
                }
                j++;
            }
            if(IsBlank.check(sheet.getRow(_RRI).getCell(i+1))){
                break;
            }
            i++;
        }
        return type;
    }
}
