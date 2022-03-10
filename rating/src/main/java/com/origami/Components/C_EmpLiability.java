package com.origami.Components;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.origami.Constants;
import com.origami.Interfaces.ASA_test.EmpLiaLimit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class C_EmpLiability {
    
    public void write(ArrayList<EmpLiaLimit> limits, String[] empLiaExlColDatatypes, String[] empLiabilityCols) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sprSheet = workbook.createSheet("Upload");
        XSSFRow row;
        for(int i=0; i<= limits.size()+1; i++){
            row = sprSheet.createRow(i);
            int cellId = 0;
            if(i == 0){
                for (String cellValue : empLiaExlColDatatypes) {
                    Cell c = row.createCell(cellId++);
                    c.setCellValue((String) cellValue);
                }
            } else if(i ==1){
                for (String cellValue : empLiabilityCols) {
                    Cell c = row.createCell(cellId++);
                    c.setCellValue((String) cellValue);
                }
            } else {
                for(int j=0; j<6;j++){
                    Cell c = row.createCell(cellId++);
                    // if(null != classCodes.get(i-2).getClassSuffix() && null != classCodes.get(i-2).getJobClassMinPremium()){
                    //     continue;
                    // }
                    switch (j) {
                        case 0:
                            c.setCellValue((String) limits.get(i-2).getBodyInjuryByAccident());
                            break;
                        case 1:
                            c.setCellValue((String) limits.get(i-2).getBodyInjuryByDiseasePL());
                            break;
                        case 2:
                            c.setCellValue((String) limits.get(i-2).getBodyInjuryByDiseaseEE());
                            break;
                        case 3:
                            double val = 0;
                            if(limits.get(i-2).getRate() != null){
                                val = Double.parseDouble((String) limits.get(i-2).getRate());
                                val = val/100; 
                            }
                            c.setCellValue(val);
                            break;
                        case 4:
                            c.setCellValue((String) limits.get(i-2).getMinPremium());
                            break;
                        case 5:
                            c.setCellValue((String) limits.get(i-2).getStatCode());
                            break;
                        default:
                            break;
                    }

                }
            }
        }
        try {
            FileOutputStream out = new FileOutputStream(new File("C:/Users/hagola/Documents/Origami/rating/src/data/States/Output/" + Constants.CURRENT_STATE + " Emp-Liability-Limits.xlsx"));
            workbook.write(out);
            System.out.println("Emp Liability Excel Generated...");
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

