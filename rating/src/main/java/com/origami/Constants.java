package com.origami;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class Constants {
    
    //***************************************** File path Config **********************************************//
    static String dir = System.getProperty("user.dir");
    public static final String FILEPATH = dir + "\\src\\data\\States\\";
    public static final String FILETYPE = ".xlsx";

    
    //***************************************** State name ****************************************************//
    public static final String CURRENT_STATE = "KS";


    //********************** Job classification, job program, emp liability variables declaration *********************************//
    static Map<String, String> classCodes = new LinkedHashMap<String, String>();
    static Map<String, String> jobClassMapValues = new LinkedHashMap<String, String>();
    static Map<String, String> jobProgramMapValues = new LinkedHashMap<String, String>();
    static Map<String, String> empLiaLimits = new LinkedHashMap<String, String>();


    //********************** Job classification, job program, emp liability datatypes for excel outputs ****************************//
    static String[] ASAjobClassExlColDatatypes = {"Text1","Text2","Number1","Number2","Number5","Number3","Text3","Text4","Number4"};
    static String[] SLjobClassExlColDatatypes = {"Text1","Text2","Number1","Number2","Number3","Text3","Text4","Number4"};    
    static String[] jobClassExlColDatatypes;    
    static String[] jobProgExlColDatatypes = {"Text1","Text2","Text3","Text4"};
    static String[] empLiaExlColDatatypes = {"Number1","Number2","Number3","Number4","Number5","Text1"};

    
    //******************************* For NCCI latest LCR rates ****************************************//
    public static final boolean useLatestRates =  false;                                // Change to true for latest rates from NCCI file
    public static final String FILE_NCCI_RATES=  "arlc070122.xlsx";                     // Provide NCCI file name (if using latest rates)
    static Integer[] ncciColumnIndexes = new Integer[]{3,13};                           // Provide indexes for classcode & LCR columns (if using latest rates)
    public static List<Integer> indexesForNCCIRates = Arrays.asList(ncciColumnIndexes);     

    
    //******************************************* Configuration for excel operations ***********************************************//
    public static final String FILE_PRODUCT_DEFINITION=  "WC Product Definition - KS NCCI.xlsx";   // Product definition filename 
    public static final int STATE_WCRATE_LC_SHEET_NO =  2;                              // Sheet number for wc rate or class codes
    public static final int ROW_READ_INDEX_FOR_WC_SHEET = 0;                            // Row number to start reading from for class codes (eg: If row number is 1, enter value as 0)
    public static final int STATE_CLASS_VAR_SHEET_NO =  4;                              // Sheet number for class variables
    public static final int ROW_READ_INDEX_FOR_CLASS_VAR_SHEET = 0;                     // Row number to start reading from for class variables
    public static final int STATE_EMP_LIABILITY_SHEET_NO =  6;                          // Sheet number for emp lia limit
    public static final int ROW_READ_INDEX_FOR_EMP_LIABILITY = 0;                       // Row number to start reading from for emp lia limit
    
    // For ASA client
    public static final boolean isClientSL =  true;                                     // For ASA change to false
    public static final String FILE_CLASS_TABLE=  "C1 Class Table by State with Available 2022 Rates.xlsx"; // Class table filename
    public static final boolean FILTER_WITH_CLASS_TABLE =  false;                       // For ASA change to true
    public static final int STATE_QUICK_CHART_SHEET_NO =  0;                            // Sheet number (if state name is not given) for class table
    public static final int ROW_READ_INDEX_FOR_CLASS_CODES = 0;                         // Row number to start reading from for class table

    
    //******************************************* Configuration for output excel ***********************************************//
    public static final String JOB_CLASSIFICATION = "Job Classification";                // Job classification output file name
    public static final String JOB_PROGRAM_CODES = "Job Program Codes";                  // Job program code output file name
    public static final String EMP_LIA_LIMITS = "Emp Lia Limits";                        // Emp lia limit output file name
    public static final String CARRIER = "SL";                                           // For service lloyd enter carrier (leave blank for ASA) 
    

    //******************************************* Predefined values as per states ***********************************************//
    public static final String LCM = "1.29";                       
    public static final String RATING_TIER =  "Standard";
       

    public static void initialize(boolean isClientSL, boolean isLatestRates){
        classCodes.put("Class Code", "Class Code");
        jobClassMapValues.put("Classification Code","Job Classification");
        jobClassMapValues.put("Class Suffix Description Code","Class Suffix");
        if(!isLatestRates) jobClassMapValues.put("Classification Manual/Loss Cost Rate","Loss Cost Rate");
        else jobClassMapValues.put("Latest Rates","Loss Cost Rate");
        jobClassMapValues.put("Classification Minimum Premium Amount","Job Class Min Premium");
        if(!isClientSL) jobClassMapValues.put("Loss Constant Amount","Loss Constant Premium");
        jobClassMapValues.put("Disease Load","Disease Load");
        jobClassMapValues.put("Contracting Class Ind","Contracting Classification");
        jobClassMapValues.put("Rating Tier","Rating Tier");
        jobClassMapValues.put("Loss Cost Multiplier","Loss Cost Multiplier");
        jobProgramMapValues.put("Classification Code","Job Classification");
        jobProgramMapValues.put("Hazard Group Code","HazardGroup");
        jobProgramMapValues.put("Program","USLH Program");
        jobProgramMapValues.put("Federal Act Status","Federal Act Status");
        empLiaLimits.put("Bodily Injury by Accident each accident", "Bodily Injury by Accident");
        empLiaLimits.put("Bodily Injury by Disease policy limit", "Bodily Injury Limit by Disease Policy Limit");
        empLiaLimits.put("Bodily Injury by Disease each employee", "Bodily Injury by Disease each Employee");
        empLiaLimits.put("Rate", "Rate");
        empLiaLimits.put("Minimum Premium", "Limit Minimum Premium");
        empLiaLimits.put("Stat Code", "Stat Code");
        if(!isClientSL) jobClassExlColDatatypes = ASAjobClassExlColDatatypes;
        else jobClassExlColDatatypes = SLjobClassExlColDatatypes;
    }
}
// if(!isClientSL){
//     states.put("IND", true);
//     states.put("TIL", true);
//     states.put("COF", true);
//     states.put("TIA", true);
//     states.put("TCT", true);
//     states.put("PHX", true);
//     states.put("ACR", true);
//     states.put("ACJ", false);
//     states.put("ASF", true);
//     states.put("AFC", false);
//     states.put("ACT", true);
//     states.put("TMO", false);
// }

// Map<String, String> deductibles = new LinkedHashMap<String, String>();
// static Map<String, Boolean> states = new LinkedHashMap<String, Boolean>();
// static Map<String, String> selectedStates = new LinkedHashMap<String, String>();

// public static final String PER_CLAIM =  "'01";
// public static final String PER_OCCURRENCE =  "'02";
// public static final String STATE =  "SLIC";
// public static final int[] ROW_READ_INDEX_FOR_COINSURANCE = {};
// public static final int[] ROW_READ_INDEX_FOR_DEDUCTIBLES = {};
