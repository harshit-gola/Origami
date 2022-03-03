package com.origami.Interfaces.ASA_test;

import com.origami.Constants;

public class JobRates {
    private String jobClassification;
    private String classSuffix;
    private String LCR;
    private String jobClassMinPremium;
    private String LossConstantPremium = "";
    private String program;
    private String diseaseLoad;
    private String contractingClass;
    private String RatingTier = "Standard";
    private String LCM = Constants.LCM;
    private String hazardGroup;
    
    public String getProgram() {
        return program;
    }
    public void setProgram(String program) {
        this.program = program;
    }
    public String getHazardGroup() {
        return hazardGroup;
    }
    public void setHazardGroup(String hazardGroup) {
        this.hazardGroup = hazardGroup;
    }
    public String getJobClassification() {
        return jobClassification;
    }
    public void setJobClassification(String jobClassification) {
        this.jobClassification = jobClassification;
    }
    public String getClassSuffix() {
        return classSuffix;
    }
    public void setClassSuffix(String classSuffix) {
        this.classSuffix = classSuffix;
    }
    public String getLCR() {
        return LCR;
    }
    public void setLCR(String lCR) {
        LCR = lCR;
    }
    public String getJobClassMinPremium() {
        return jobClassMinPremium;
    }
    public void setJobClassMinPremium(String jobClassMinPremium) {
        this.jobClassMinPremium = jobClassMinPremium;
    }
    public String getLossConstantPremium() {
        return LossConstantPremium;
    }
    public void setLossConstantPremium(String lossConstantPremium) {
        LossConstantPremium = lossConstantPremium;
    }
    public String getDiseaseLoad() {
        return diseaseLoad;
    }
    public void setDiseaseLoad(String diseaseLoad) {
        this.diseaseLoad = diseaseLoad;
    }
    public String getContractingClass() {
        return contractingClass;
    }
    public void setContractingClass(String contractingClass) {
        this.contractingClass = contractingClass;
    }
    public String getRatingTier() {
        return RatingTier;
    }
    public void setRatingTier(String ratingTier) {
        RatingTier = ratingTier;
    }
    public String getLCM() {
        return LCM;
    }
    public void setLCM(String lCM) {
        LCM = lCM;
    }
}
