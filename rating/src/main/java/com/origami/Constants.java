package com.origami;

public final class Constants {
        
    private Constants(){}

    static String dir = System.getProperty("user.dir");

    //file config
    public static final String FILEPATH = dir + "\\rating\\src\\data\\";
    public static final String FILETYPE = ".xlsx";
    

    //******************************************* Service_lloyd ***********************************************//
    public static final int WCRATE_LC_SHEET_NO = 3;
    
    //******************************************* ASA_test ***********************************************//
    
    //indexes
    //For Job Classification Rate 
    public static final int ROW_READ_INDEX_FOR_CLASS_CODES = 0;
    public static final int ROW_READ_INDEX_FOR_WC_SHEET = 0;
    public static final int ROW_READ_INDEX_FOR_CLASS_VAR_SHEET = 0;
    public static final int ROW_READ_INDEX_FOR_EMP_LIABILITY = 0;
    public static final int COL_VALIDATION_INDEX_FOR_CLASS_CODES = 1;
    public static final int COL_VALIDATION_INDEX_FOR_WC_SHEET = 4;
    public static final int COL_VALIDATION_INDEX_FOR_CLASS_VARIABLE = 1;
    public static final int COL_VALIDATION_INDEX_FOR_EMP_LIABILITY = 2;
    
    //sheet no.s & name
    public static final int STATE_WCRATE_LC_SHEET_NO =  2;
    public static final int STATE_CLASS_VAR_SHEET_NO =  4;          //For KS State
    // public static final int STATE_CLASS_VAR_SHEET_NO =  5;       //For IN State
    public static final int STATE_EMP_LIABILITY_SHEET_NO =  6;      //For KS State    
    // public static final int STATE_EMP_LIABILITY_SHEET_NO =  8;   //For IN State
    public static final int STATE_QUICK_CHART_SHEET_NO =  0;
    public static final String STATE_WCRATE_LC_SHEET_NAME = "KS";
    
    //state wise config
    public static final String CURRENT_STATE = "KS";
    public static final String LCM = "1.356";                       //For KS State
    // public static final String LCM = "1.348";                    //For IN State
    public static final String FILE_CLASS_TABLE=  "KS State\\C1 Class Table by State with Available 2022 Rates";
    public static final String FILE_PRODUCT_DEFINITION=  "KS State\\PK WC Product Definition - KS NCCI 20210423";
    // public static final String FILE_PRODUCT_DEFINITION=  "KS State\\PK WC Product Definition - IN WCB 20210630";
    public static final String FILE_COMPANY_CHART=  "KS State\\Company Usage chart for Agri 09-07-21 (2)";
    
}
