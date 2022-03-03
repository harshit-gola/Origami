package com.origami;

import java.io.IOException;
import java.util.ArrayList;

import com.origami.Components.Op_ClassCodes;
import com.origami.Components.Op_ClassVar;
import com.origami.Components.C_EmpLiability;
import com.origami.Components.Op_EmpLiability;
import com.origami.Components.Op_WCRate;
import com.origami.Components.C_JobClassification;
import com.origami.Components.C_JobProgram;
import com.origami.Interfaces.ASA_test.EmpLiaLimit;
import com.origami.Interfaces.ASA_test.JobRates;


public class Controller {

    static ArrayList<JobRates> classCodes = new ArrayList<JobRates>();
    static ArrayList<EmpLiaLimit> limits = new ArrayList<EmpLiaLimit>();
    static String[] jobClassExcelColumns = {"Job Classification","Class Suffix","Loss Cost Rate","Job Class Min Premium","Loss Constant Premium","Disease Load","Contracting Classification","Rating Tier","Loss Cost Multiplier"};
    static String[] jobClassExlColDatatypes = {"Text1","Text2","Number1","Number2","Number5","Number3","Text3","Text4","Number4"};
    static String[] jobProgExlColDatatypes = {"Text1","Text2","Text3","Text4"};
    static String[] jobProgExlColColumns = {"Job Classification","HazardGroup","USLH Program","Federal Act Status"};
    static String[] empLiaExlColDatatypes = {"Number1","Number2","Number3","Number4","Number5","Text1"};
    static String[] empLiabilityCols = {"Bodily Injury by Accident each accident","Bodily Injury by Disease policy limit","Bodily Injury by Disease each employee","Rate","Minimum Premium","Stat Code"};
    
    public static void perform() throws IOException {

        String[] clsTblCols = {"Class Code"};
        String[] wcRateCols = {"Classification Code", "Class Suffix Description Code" 
                            , "Classification Manual/Loss Cost Rate", "Classification Minimum Premium Amount"
                            , "Loss Constant Amount", "Hazard Group Code"};
        String[] classVarCols = {"Classification Code", "Program", "Disease Load", "Contracting Class Ind"};
        // String[] quickChartCols = {"State", "Base LCM", "IND", "TIL", "COF", "TIA", "TCT", "PHX", "ACR", "ACJ",
        //                             "ASF", "AFC", "ACT", "TMO"};

                                    
        boolean isWcRateSet = false;
        boolean isClassVarSet = false;
        
        Op_ClassCodes extractedClassCodes = new Op_ClassCodes(Constants.STATE_WCRATE_LC_SHEET_NO,
                                                                    Constants.STATE_WCRATE_LC_SHEET_NAME,
                                                                    clsTblCols, Constants.ROW_READ_INDEX_FOR_CLASS_CODES, Constants.COL_VALIDATION_INDEX_FOR_CLASS_CODES);
        classCodes = extractedClassCodes.run();
        if(classCodes != null){
            Op_WCRate wcRates = new Op_WCRate(Constants.STATE_WCRATE_LC_SHEET_NO,"",wcRateCols, Constants.ROW_READ_INDEX_FOR_WC_SHEET, Constants.COL_VALIDATION_INDEX_FOR_WC_SHEET);
            isWcRateSet = wcRates.setWCRate(classCodes);

            Op_ClassVar classVars = new Op_ClassVar(Constants.STATE_CLASS_VAR_SHEET_NO, "",classVarCols, Constants.ROW_READ_INDEX_FOR_CLASS_VAR_SHEET, Constants.COL_VALIDATION_INDEX_FOR_CLASS_VARIABLE);
            isClassVarSet = classVars.setClassVariable(classCodes);                                            
        }

        Op_EmpLiability instance = new Op_EmpLiability(Constants.STATE_EMP_LIABILITY_SHEET_NO, ""
                                                        , empLiabilityCols, Constants.ROW_READ_INDEX_FOR_EMP_LIABILITY
                                                        , Constants.COL_VALIDATION_INDEX_FOR_EMP_LIABILITY);
        limits = instance.getLimits();
        

        // ExtractStateValues stateVals = new ExtractStateValues(Constants.STATE_QUICK_CHART_SHEET_NO, "", quickChartCols, 11, 1);
        // stateVals.run();

        System.out.println("~~~~~~~~~~~~~~~~~~ Third Sheet Result ~~~~~~~~~~~~~~~~");
                System.out.print("Job class" + "    ");
                    System.out.print("Class suffix" + "    ");
                    System.out.print("LCR" + "    ");
                    System.out.print("Class min " + "    ");
                    System.out.print("LC pre" + "    ");
                    System.out.print("Program" + "    ");
                    System.out.print("Disease" + "    ");
                    System.out.print("Contract" + "    ");
                    System.out.print("Rating" + "    ");
                    System.out.print("Hz Group" + "    ");
                    System.out.print("LCM" + "    ");
                    System.out.println();
                for (JobRates object : classCodes) {
                    System.out.print(object.getJobClassification()+ "    ");
                    System.out.print(object.getClassSuffix()+ "    ");
                    System.out.print(object.getLCR()+ "    ");
                    System.out.print(object.getJobClassMinPremium()+ "    ");
                    System.out.print(object.getLossConstantPremium()+ "    ");
                    System.out.print(object.getProgram()+ "    ");
                    System.out.print(object.getDiseaseLoad()+ "    ");
                    System.out.print(object.getContractingClass()+ "    ");
                    System.out.print(object.getRatingTier()+ "    ");
                    System.out.print(object.getHazardGroup()+ "    ");
                    System.out.print(object.getLCM()+ "    ");
                    System.out.println();
                }

                if(isWcRateSet && isClassVarSet && limits.size() != 0){
                    writeToExcel();
                }
    }


    private static void writeToExcel() {
        C_JobClassification jc = new C_JobClassification();
        jc.write(classCodes,jobClassExlColDatatypes,jobClassExcelColumns);

        C_JobProgram jp = new C_JobProgram();
        jp.write(classCodes, jobProgExlColDatatypes, jobProgExlColColumns);

        C_EmpLiability el = new C_EmpLiability();
        el.write(limits, empLiaExlColDatatypes, empLiabilityCols);

    }
    
}
