// package com.origami;

// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.Collection;
// import java.util.Collections;
// import java.util.HashSet;
// import java.util.Iterator;
// import java.util.Map;
// import java.util.Set;

// import com.origami.Interfaces.WCRateLC;
// import com.origami.Interfaces.ASA_test.JobRates;

// import org.apache.poi.ss.usermodel.DataFormatter;
// import org.apache.poi.ss.usermodel.Row;
// import org.apache.poi.xssf.usermodel.XSSFRow;
// import org.apache.poi.xssf.usermodel.XSSFSheet;
// import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// public class Ops implements Runnable {

//     XSSFWorkbook workbook;
//     XSSFSheet sheet;
//     int _CVI = 0;
//     int _RRI = Constants.ROW_READ_INDEX;
//     DataFormatter formatter = new DataFormatter();
//     static ArrayList<JobRates> dCellDataList = new ArrayList<JobRates>();
//     static ArrayList<JobRates> cellDataList = new ArrayList<JobRates>();
//     int currentFileI = 0;
//     String[] cols = null;

//     String[] classTableColumns = {"Class Code"};
//     String[] allowedColumns = {"Classification Code", "Class Suffix Description Code" 
//                             ,"Classification Manual/Loss Cost Rate", "Classification Minimum Premium Amount"};
//     String[] aC = {"Disease Load","Contracting Class Ind"};
//     String[] aCd = {"LCM"};

//     public Ops(int sheetNo, String sheetName, int fileNum, Map<Integer, String[]> columnsToExtract, int instanceNo) throws IOException{
//         cols = columnsToExtract.get(instanceNo);
//         currentFileI = instanceNo;
//         String excelPath = null;

//         switch (fileNum) {
//             case 1:
//                 excelPath = Constants.FILEPATH + Constants.FILE_CLASS_TABLE + Constants.FILETYPE;            
//                 break;
//             case 2:
//                 excelPath = Constants.FILEPATH + Constants.FILE_PRODUCT_DEFINITION + Constants.FILETYPE;
//                 break;
//             case 3:
//                 excelPath = Constants.FILEPATH + Constants.FILE_COMPANY_CHART + Constants.FILETYPE;
//             default:
//                 break;
//         }

//         switch (instanceNo) {
//             case 1:
//                 _CVI = Constants.COL_VALIDATION_INDEX_CLASS_TABLE;
//                 break;
//             case 2:
//                 _CVI = Constants.COL_VALIDATION_INDEX_WCRATE;
//                 break;
//             case 3:
//                 _CVI = Constants.COL_VALIDATION_INDEX_CLASS_VARIABLE;
//                 break;
//             default:
//                 break;
//         }
//         workbook = new XSSFWorkbook(excelPath);
//         if(sheetName.equals("") || sheetName.isBlank() || sheetName.isEmpty())
//             sheet = workbook.getSheetAt(sheetNo);
//         else
//             sheet = workbook.getSheet(sheetName);    
//     } 

//     @Override
//     public void run(){
//         ArrayList<Integer> headers = getHeaders(cols);

//         getCellData(headers);

//         switch (currentFileI) {

//             case 1:
//                 System.out.println("~~~~~~~~~~~~~~~~~~ First Sheet Result ~~~~~~~~~~~~~~~~");
//                 for (JobRates object : dCellDataList) {
//                     System.out.print(object.getJobClassification()+ "    ");
//                     System.out.println();
//                 }
//                 break;

//             case 2:
//                 System.out.println("~~~~~~~~~~~~~~~~~~ Scnd Sheet Result ~~~~~~~~~~~~~~~~");
//                 for (JobRates object : cellDataList) {
//                     System.out.print(object.getJobClassification()+ "    ");
//                     System.out.print(object.getClassSuffix()+ "    ");
//                     System.out.print(object.getLCR()+ "    ");
//                     System.out.print(object.getJobClassMinPremium()+ "    ");
//                     System.out.print(object.getLossConstantPremium()+ "    ");
//                     System.out.println();
//                 }
//                 break;
            
//             case 3:
//                 System.out.println("~~~~~~~~~~~~~~~~~~ Third Sheet Result ~~~~~~~~~~~~~~~~");
//                 System.out.print("Job class" + "    ");
//                     System.out.print("Class suffix" + "    ");
//                     System.out.print("LCR" + "    ");
//                     System.out.print("Class min " + "    ");
//                     System.out.print("LC pre" + "    ");
//                     System.out.print("Program" + "    ");
//                     System.out.print("Disease" + "    ");
//                     System.out.print("Contract" + "    ");
//                     System.out.print("Rating" + "    ");
//                     System.out.print("Hz Group" + "    ");
//                     System.out.print("LCM" + "    ");
//                     System.out.println();
//                 for (JobRates object : cellDataList) {
//                     System.out.print(object.getJobClassification()+ "    ");
//                     System.out.print(object.getClassSuffix()+ "    ");
//                     System.out.print(object.getLCR()+ "    ");
//                     System.out.print(object.getJobClassMinPremium()+ "    ");
//                     System.out.print(object.getLossConstantPremium()+ "    ");
//                     System.out.print(object.getProgram()+ "    ");
//                     System.out.print(object.getDiseaseLoad()+ "    ");
//                     System.out.print(object.getContractingClass()+ "    ");
//                     System.out.print(object.getRatingTier()+ "    ");
//                     System.out.print(object.getHazardGroup()+ "    ");
//                     System.out.print(object.getLCM()+ "    ");
//                     System.out.println();
//                 }
//                 break;

//             default:
//                 break;
//         }
        
//         try {
//             workbook.close();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     public ArrayList<Integer> getHeaders(String[] allowedColumns) {
        
//         int i = 0;
//         // int rows = getRowsAndColumns(sheet, "ROW");
//         int columns = getRowsAndColumns(sheet, "COLUMN");

//         ArrayList<Integer> selectedColumns = new ArrayList<Integer>();

//         // System.out.println("total numbers of rows are: "+ rows);
//         System.out.println("total numbers of columns are: "+ columns);
        
//         while(i != columns){
//             Object value = formatter.formatCellValue(sheet.getRow(_RRI).getCell(i));
//             if(Arrays.asList(allowedColumns).contains(value)){
//                 selectedColumns.add(i);
//                 Collections.sort(selectedColumns);
//             }
//             i++;
//         }
        
//         System.out.println("Selected columns are : "+ selectedColumns);
//         return selectedColumns;
//     }

//     private int getRowsAndColumns(XSSFSheet sheet, String type){
//         int rowCount = 0;
//         int colCount = 0;
//         XSSFRow row = sheet.getRow(_RRI);
//         Iterator<Row> rIterator = sheet.rowIterator();
//         Iterator<org.apache.poi.ss.usermodel.Cell> cIterator = row.cellIterator();
        
//         if(type.equalsIgnoreCase("ROW")){
//             while(rIterator.hasNext()){
//                 if(isBlank(rIterator.next().getCell(_CVI))){
//                     break;
//                 }
//                 rowCount++;
//             }
//             return rowCount;
//         }
//         else{
//             while(cIterator.hasNext()){
//                 if(isBlank(cIterator.next())){
//                     break;
//                 }
//                 colCount++;
//             }
//             return colCount;
//         }
//     }

//     private void getCellData(ArrayList<Integer> headers){
//         Iterator<Row> rIterator = sheet.rowIterator();
//         int headerLength = headers.size();
//         // ArrayList<JobRates> cellDataList = new ArrayList<JobRates>();
//         boolean i = true;
        
//         while(rIterator.hasNext()){
//             while(i){
//                 rIterator.next();
//                 i = false;
//             }
//             Row row = rIterator.next();
//             Iterator<Integer> header = headers.iterator();
//             JobRates cluster = new JobRates();


//             if(!isBlank(row.getCell(_CVI))){
//                 switch (currentFileI) {
//                     case 1:
//                         dCellDataList.add(getASATestClassTableVal(row, header));
//                         break;
//                     case 2:
//                         cluster = getASATestClassTableVal(row, header);
//                         if(cluster != null)
//                             cellDataList.add(cluster);
//                         break;
//                     case 3:
//                         setASATestClassVarVal(row, header);
//                     default:
//                         break;
//                 }  
//             } else {
//                 break;  
//             }
//         }
//     }

//     private JobRates getASATestClassTableVal(Row row, Iterator<Integer> header){
//         int j = -1;
//         boolean isPresent = false;
//         JobRates cluster = new JobRates();
//         while(header.hasNext()){
//             Object val = formatter.formatCellValue(row.getCell(header.next()));
//             // list.add(val);
//             j++;

//             if(currentFileI == 1){
//                 isPresent = true;
//             }

//             if(!isPresent){
//                 for(int z = 0; z < dCellDataList.size(); z++){
//                     if(dCellDataList.get(z).getJobClassification().equalsIgnoreCase((String) val) && j == 0){
//                         isPresent = true;
//                         break;
//                     }
//                 }
//             }

//             if(isPresent){
//                 switch (j) {
//                     case 0:
//                         cluster.setJobClassification((String) val);
//                         break;
//                     case 1:
//                         cluster.setClassSuffix((String) val);
//                         break;
//                     case 2:
//                         cluster.setLCR((String) val);
//                         break;
//                     case 3:
//                         cluster.setJobClassMinPremium((String) val);
//                         break;
//                     case 4:
//                         cluster.setLossConstantPremium((String) val);
//                         break;
//                     case 5:
//                         cluster.setHazardGroup((String) val);
//                     default:
//                         break;
//                 }
//             } else return null;
//         }
//         return cluster;
//     }

//     private void setASATestClassVarVal(Row row, Iterator<Integer> header){
//         int j = -1;
//         boolean isPresent = false;
//         int index = 0;
//         while(header.hasNext()){
//             Object val = formatter.formatCellValue(row.getCell(header.next()));
//             // list.add(val);
//             j++;

//             if(!isPresent){
//                 for(int z = 0; z < cellDataList.size(); z++){
//                     if(cellDataList.get(z).getJobClassification().equalsIgnoreCase((String) val) && j == 0){
//                         isPresent = true;
//                         index = z;
//                         break;
//                     }
//                 }
//             }

//             if(isPresent){
//                 switch (j) {
//                     case 0:
//                         break;
//                     case 1:
//                         cellDataList.get(index).setProgram((String) val);
//                         break;
//                     case 2:
//                         cellDataList.get(index).setDiseaseLoad((String) val);
//                         break;
//                     case 3:
//                         cellDataList.get(index).setContractingClass((String) val);
//                         break;
//                     default:
//                         break;
//                 }
                
//             }
//         }
//     }
    
//     private WCRateLC getServiceLloydWCRateLCCluster(Row row, Iterator<Integer> header) {
//         int j = -1;
//         WCRateLC cluster = new WCRateLC();
//         while(header.hasNext()){
//             Object val = formatter.formatCellValue(row.getCell(header.next()));
//             // list.add(val);
//             j++;
//             switch (j) {
//                 case 0:
//                     cluster.setClassificationCode((String) val);
//                     break;
//                 case 1:
//                     cluster.setClassSuffDescCode((String) val);
//                     break;
//                 case 2:
//                     cluster.setLCR((String) val);
//                     break;
//                 case 3:
//                     cluster.setClassificationMinPremiumAmt((String) val);
//                     break;
//                 default:
//                     break;
//             }
//         }
//         return cluster;
//     }


//     private boolean isBlank(org.apache.poi.ss.usermodel.Cell c) {
//         if(c == null || c.getCellType() == org.apache.poi.ss.usermodel.CellType.BLANK)
//             return true;
//         else
//             return false;
//     }

//     private ArrayList<JobRates> getFilteredList(ArrayList<JobRates> allValues,
//             ArrayList<JobRates> selectedClsCds) {
//             ArrayList<JobRates> arr = new ArrayList<>();

//             for (JobRates d : allValues) {
//                 for (JobRates e : selectedClsCds) {
//                     if(d.getJobClassification().equalsIgnoreCase(e.getJobClassification()))
//                     arr.add(d);
//             }
//         }
//         return arr;
//     }
// }


