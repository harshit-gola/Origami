// package com.origami.Utils;

// import java.io.File;
// import java.io.FileNotFoundException;
// import java.io.FileOutputStream;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.Iterator;

// import com.origami.Constants;

// import org.apache.logging.log4j.core.tools.picocli.CommandLine.Help.TextTable.Cell;
// import org.apache.poi.ss.usermodel.DataFormatter;
// import org.apache.poi.ss.usermodel.Row;
// import org.apache.poi.xssf.usermodel.XSSFCell;
// import org.apache.poi.xssf.usermodel.XSSFRow;
// import org.apache.poi.xssf.usermodel.XSSFSheet;
// import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// public class ReadRow {
    
//     String excelPath = Constants.FILEPATH + Constants.FILE_COMPANY_CHART + Constants.FILETYPE;
//     int sheetNo = 0;
//     String sheetName = null;
//     int _RRI = 0;

//     DataFormatter formatter = new DataFormatter();
//     ArrayList<String> values = new ArrayList<>();
//     String[] tieredDatatypes = {"Lookup1", "Number4"};
//     String[] tieredCols = {"Tiered Company", "Tiered Deviation"};
//     String[] states = {"IND", "TIL", "COF", "TIA", "TCT", "PHX", "ACR", "ACJ","ASF", "AFC", "ACT", "TMO"};

//     public ReadRow(int sheetNo, int _RRI){
//         this.sheetNo = sheetNo;
//         this._RRI = _RRI;
//     }

//     public void run() throws IOException{
//         XSSFWorkbook wb = new XSSFWorkbook(excelPath);
//         XSSFSheet sheet = wb.getSheetAt(this.sheetNo);
//         Row r = sheet.getRow(this._RRI);
//         Iterator<org.apache.poi.ss.usermodel.Cell> cIterator = r.iterator();
        
//         while(cIterator.hasNext()){
//             Object ob = formatter.formatCellValue(cIterator.next());
//             this.values.add((String) ob);
//         }

//     }

//     public void exportToTieredExl(){
//         XSSFWorkbook workbook = new XSSFWorkbook();
//         XSSFSheet sprSheet = workbook.createSheet("Upload");
//         XSSFRow row;
        
//         for(int i=0; i< 12; i++){
//             int cellId = 0;
//             row = sprSheet.createRow(i);
//             if(i == 0){
//                 for (String cellValue : tieredDatatypes) {
//                     XSSFCell c = row.createCell(cellId++);
//                     c.setCellValue((String) cellValue);
//                 }
//             } else if(i ==1){
//                 for (String cellValue : tieredCols) {
//                     XSSFCell c = row.createCell(cellId++);
//                     c.setCellValue((String) cellValue);
//                 }
//             } else {
//                 for(int j=0; j<2;j++){
//                     XSSFCell c = row.createCell(cellId++);
//                     switch (j) {
//                         case 0:
//                             c.setCellValue(this.states[i-2]);
//                             break;
//                         case 1:
//                             double val = convertToDec(this.values.get(i+4));
//                             c.setCellValue(convertToPercent(val));
//                             break;
//                         default:
//                             break;
//                     }

//                 }
//             }
//             if(this.values.size() == i+4) break;
//         }
//         try {
//             FileOutputStream out = new FileOutputStream(new File("C:/Users/hagola/Documents/Origami/rating/src/data/States/Output/" + Constants.CURRENT_STATE + " Tiered.xlsx"));
//             workbook.write(out);
//             System.out.println("Emp Liability Excel Generated...");
//             out.close();
//         } catch (FileNotFoundException e) {
//             e.printStackTrace();
//         } catch (IOException e) {
//             e.printStackTrace();
//         } finally{
//             try {
//                 workbook.close();
//             } catch (IOException e) {
//                 e.printStackTrace();
//             }
//         }
//     }

//     private double convertToDec(String val){
//         double d = Double.parseDouble(val.substring(0, val.length()-1));
//         return d;
//     }

//     private double convertToPercent(double val){
//         double p = 100 + val;
//         p = p/100;
//         return p;
//     }
// }
