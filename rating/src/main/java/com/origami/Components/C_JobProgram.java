package com.origami.Components;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.origami.Constants;
import com.origami.Interfaces.ASA_test.JobRates;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class C_JobProgram {
    
    public void write(ArrayList<JobRates> classCodes, String[] jobProgExlColDatatypes, String[] jobProgExlColColumns) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sprSheet = workbook.createSheet("Upload");
        XSSFRow row;
        for(int i=0; i<= classCodes.size()+1; i++){
            row = sprSheet.createRow(i);
            int cellId = 0;
            if(i == 0){
                for (String cellValue : jobProgExlColDatatypes) {
                    Cell c = row.createCell(cellId++);
                    c.setCellValue((String) cellValue);
                }
            } else if(i ==1){
                for (String cellValue : jobProgExlColColumns) {
                    Cell c = row.createCell(cellId++);
                    c.setCellValue((String) cellValue);
                }
            } else {
                for(int j=0; j<4;j++){
                    Cell c = row.createCell(cellId++);
                    // if(null != classCodes.get(i-2).getClassSuffix() && null != classCodes.get(i-2).getJobClassMinPremium()){
                    //     continue;
                    // }
                    switch (j) {
                        case 0:
                            c.setCellValue((String) classCodes.get(i-2).getJobClassification());
                            break;
                        case 1:
                            c.setCellValue((String) classCodes.get(i-2).getHazardGroup());
                            break;
                        case 2:
                            c.setCellValue((String) classCodes.get(i-2).getProgram());
                            break;
                        case 3:
                            c.setCellValue((String) null);
                            break;
                        default:
                            break;
                    }

                }
            }
        }
        try {
            FileOutputStream out = new FileOutputStream(new File("C:/Users/hagola/Documents/Origami/rating/src/data/States/Output/" + Constants.CURRENT_STATE + "Job PRogram.xlsx"));
            workbook.write(out);
            System.out.println("Job Program Excel Generated...");
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

