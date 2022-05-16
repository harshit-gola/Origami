package com.origami;

import java.util.List;
import java.util.Map;


public class Controller {

    public static void perform(){
        try {
            Constants.initialize(Constants.isClientSL,Constants.useLatestRates);
            Map<Integer, List<String>> list2Print;
            Ops instance;
            Ops newRates;            
            Ops readJobClassWCSheet = new Ops(Constants.STATE_WCRATE_LC_SHEET_NO, "", Constants.ROW_READ_INDEX_FOR_WC_SHEET, Constants.FILEPATH + Constants.FILE_PRODUCT_DEFINITION);
            Ops readJobProgWCSheet = new Ops(Constants.STATE_WCRATE_LC_SHEET_NO, "", Constants.ROW_READ_INDEX_FOR_WC_SHEET, Constants.FILEPATH + Constants.FILE_PRODUCT_DEFINITION);
            Ops readJobClassVarSheet = new Ops(Constants.STATE_CLASS_VAR_SHEET_NO, "", Constants.ROW_READ_INDEX_FOR_CLASS_VAR_SHEET, Constants.FILEPATH + Constants.FILE_PRODUCT_DEFINITION);
            Ops readJobProgVarSheet = new Ops(Constants.STATE_CLASS_VAR_SHEET_NO, "", Constants.ROW_READ_INDEX_FOR_CLASS_VAR_SHEET, Constants.FILEPATH + Constants.FILE_PRODUCT_DEFINITION);
            Ops empSheet = new Ops(Constants.STATE_EMP_LIABILITY_SHEET_NO, "", Constants.ROW_READ_INDEX_FOR_EMP_LIABILITY, Constants.FILEPATH + Constants.FILE_PRODUCT_DEFINITION);
            readJobClassWCSheet.getColumnIndexesFromExcelAndSet(Constants.jobClassMapValues);
            readJobProgWCSheet.getColumnIndexesFromExcelAndSet(Constants.jobProgramMapValues);
            readJobClassVarSheet.getColumnIndexesFromExcelAndSet(Constants.jobClassMapValues);
            readJobProgVarSheet.getColumnIndexesFromExcelAndSet(Constants.jobProgramMapValues);
            empSheet.getColumnIndexesFromExcelAndSet(Constants.empLiaLimits);
            readJobClassWCSheet.getFromExcelAndSet();
            readJobProgWCSheet.getFromExcelAndSet();
            readJobClassVarSheet.getFromExcelAndSet();
            readJobProgVarSheet.getFromExcelAndSet();
            empSheet.getFromExcelAndSet();
            Map<Integer, List<String>> dataFromWcSheetForJobClass = readJobClassWCSheet.getData();
            Map<Integer, List<String>> dataFromWcSheetForJobProg = readJobProgWCSheet.getData();
            Map<Integer, List<String>> dataFromClassVarForJobClass = readJobClassVarSheet.getData();
            Map<Integer, List<String>> dataFromClassVarForJobProg = readJobProgVarSheet.getData();
            Map<Integer, List<String>> mappedList = Ops.mapData(dataFromWcSheetForJobClass,dataFromWcSheetForJobProg);
            Map<Integer, List<String>> mappedList1 = Ops.mapData(dataFromClassVarForJobClass,dataFromClassVarForJobProg);
            Map<Integer, List<String>> mappedList2 = Ops.mapData(mappedList,mappedList1);
            if(Constants.useLatestRates){
                newRates = new Ops(0,"",0,Constants.FILEPATH+ Constants.FILE_NCCI_RATES);  
                newRates.useLatestRates();
                newRates.getColumnIndexesFromExcelAndSet(Constants.indexesForNCCIRates);
                newRates.getFromExcelAndSet();
                mappedList2 = Ops.mapData(mappedList2,newRates.getData());
            }
            if(Constants.isClientSL){
                list2Print = mappedList2;
            } else {
                if(Constants.FILTER_WITH_CLASS_TABLE){
                    instance = new Ops(Constants.STATE_QUICK_CHART_SHEET_NO, Constants.CURRENT_STATE, Constants.ROW_READ_INDEX_FOR_CLASS_CODES, Constants.FILEPATH + Constants.FILE_CLASS_TABLE);
                    instance.getColumnIndexesFromExcelAndSet(Constants.classCodes);
                    instance.getFromExcelAndSet();
                    list2Print = Ops.filterData(instance.getData(),mappedList2);
                } else {
                    list2Print = mappedList2;
                }
                
            }            
            if(Constants.useLatestRates) Ops.writeToExcel(Constants.jobClassExlColDatatypes, Constants.jobClassMapValues, list2Print, " -" + Constants.CARRIER + " " + Constants.JOB_CLASSIFICATION + " New Rates");
            else Ops.writeToExcel(Constants.jobClassExlColDatatypes, Constants.jobClassMapValues, list2Print, " -" + Constants.CARRIER + " " + Constants.JOB_CLASSIFICATION);
            Ops.writeToExcel(Constants.jobProgExlColDatatypes, Constants.jobProgramMapValues, list2Print, " -" + Constants.CARRIER + " " + Constants.JOB_PROGRAM_CODES);
            Ops.writeToExcel(Constants.empLiaExlColDatatypes , Constants.empLiaLimits, empSheet.getData(), " -" + Constants.CARRIER + " " + Constants.EMP_LIA_LIMITS);
            System.out.println();
        } catch (Exception e) {
            System.out.println("Exception in controller");
            e.printStackTrace();
        }
    }
}

        // Map<String, String> deductibles = new LinkedHashMap<String, String>();
        // deductibles.put("Hazard Group","Hazard Group");
        // deductibles.put("Deductible Amount","Deductible Amount");
        // deductibles.put("Premium Reduction Credit Percentages","Discount Percent");
        // deductibles.put("Deductible Amount","Deductible Amount2");

        // Map<String, String> deductiblesPerClaim = new LinkedHashMap<String, String>(deductibles);
        // Map<String, String> deductiblesPerOcc = new LinkedHashMap<String, String>(deductibles);


        // Ops empLiaInstance = new Ops(6, "", 0, Constants.FILEPATH+ "WC Product Definition - CO NCCI.xlsx");
        // Ops deductibleInstance = new Ops(6, "CO Deductibles 2022", 4, Constants.FILEPATH+ "WC Product Definition - CO NCCI.xlsx");

        // deductibleInstance.getColumnIndexesFromExcelAndSet(deductiblesPerClaim);
        // deductibleInstance.getFromExcelAndSet();
        // Ops ksDe = new Ops(4, "KS Deductibles 2021", 7, Constants.FILEPATH+ "PK WC Product Definition - KS NCCI 20210423.xlsx");
        // ksDe.getColumnIndexesFromExcelAndSet(deductiblesPerClaim);
        // Ops instancee1 = new Ops(2, "", 0, Constants.FILEPATH+ "PK WC Product Definition - CO NCCI 20210616.xlsx");

        // readWcRates.filterWith(instance.getData());

        // Ops.writeToExcel(SLdeductiblesDatatypes, deductiblesPerClaim, deductibleInstance.getData(), " - SA Deductible");
        // Controller.perform();
        // ReadRow rr = new ReadRow(0, 29);
        // rr.run();
        // rr.exportToTieredExl();