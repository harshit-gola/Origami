// package com.origami;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.LinkedHashMap;
// import java.util.List;
// import java.util.Map;

// public class Variables {
    
//     static Map<String, String> classCodes = new LinkedHashMap<String, String>();
//     static Map<String, String> jobClassMapValues = new LinkedHashMap<String, String>();
//     static Map<String, String> jobProgramMapValues = new LinkedHashMap<String, String>();
//     Map<String, String> deductibles = new LinkedHashMap<String, String>();
//     static Map<String, String> empLiaLimits = new LinkedHashMap<String, String>();
//     static Map<String, Boolean> states = new LinkedHashMap<String, Boolean>();
//     static Map<String, String> selectedStates = new LinkedHashMap<String, String>();

//     static String[] ASAjobClassExlColDatatypes = {"Text1","Text2","Number1","Number2","Number5","Number3","Text3","Text4","Number4"};
//     static String[] SLjobClassExlColDatatypes = {"Text1","Text2","Number1","Number2","Number3","Text3","Text4","Number4"};    
//     static String[] jobClassExlColDatatypes;    
//     static String[] jobProgExlColDatatypes = {"Text1","Text2","Text3","Text4"};
//     static String[] empLiaExlColDatatypes = {"Number1","Number2","Number3","Number4","Number5","Text1"};
//     // static String[] states = {"IND", "TIL", "COF", "TIA", "TCT", "PHX", "ACR", "ACJ","ASF", "AFC", "ACT", "TMO"};
//     public static List<Integer> indexesForNCCIRates = new ArrayList<>(Arrays.asList(3,13));
		

//     public static void initialize(boolean isClientSL, boolean isLatestRates){
//         classCodes.put("Class Code", "Class Code");

//         jobClassMapValues.put("Classification Code","Job Classification");
//         jobClassMapValues.put("Class Suffix Description Code","Class Suffix");
//         if(!isLatestRates) jobClassMapValues.put("Classification Manual/Loss Cost Rate","Loss Cost Rate");
//         else jobClassMapValues.put("Latest Rates","Loss Cost Rate");
//         jobClassMapValues.put("Classification Minimum Premium Amount","Job Class Min Premium");
//         if(!isClientSL) jobClassMapValues.put("Loss Constant Amount","Loss Constant Premium");
//         jobClassMapValues.put("Disease Load","Disease Load");
//         jobClassMapValues.put("Contracting Class Ind","Contracting Classification");
//         jobClassMapValues.put("Rating Tier","Rating Tier");
//         jobClassMapValues.put("Loss Cost Multiplier","Loss Cost Multiplier");
        
//         jobProgramMapValues.put("Classification Code","Job Classification");
//         jobProgramMapValues.put("Hazard Group Code","HazardGroup");
//         jobProgramMapValues.put("Program","USLH Program");
//         jobProgramMapValues.put("Federal Act Status","Federal Act Status");

//         empLiaLimits.put("Bodily Injury by Accident each accident", "Bodily Injury by Accident");
//         empLiaLimits.put("Bodily Injury by Disease policy limit", "Bodily Injury Limit by Disease Policy Limit");
//         empLiaLimits.put("Bodily Injury by Disease each employee", "Bodily Injury by Disease each Employee");
//         empLiaLimits.put("Rate", "Rate");
//         empLiaLimits.put("Minimum Premium", "Limit Minimum Premium");
//         empLiaLimits.put("Stat Code", "Stat Code");

//         if(!isClientSL) jobClassExlColDatatypes = ASAjobClassExlColDatatypes;
//         else jobClassExlColDatatypes = SLjobClassExlColDatatypes;

//         // if(!isClientSL){
//         //     states.put("IND", true);
//         //     states.put("TIL", true);
//         //     states.put("COF", true);
//         //     states.put("TIA", true);
//         //     states.put("TCT", true);
//         //     states.put("PHX", true);
//         //     states.put("ACR", true);
//         //     states.put("ACJ", false);
//         //     states.put("ASF", true);
//         //     states.put("AFC", false);
//         //     states.put("ACT", true);
//         //     states.put("TMO", false);
//         // }
//     }

//     private static void initializeStates(){
//         // for (String state : states.keySet()) {
//         //     if(state)
//         // }
//     }
// }
