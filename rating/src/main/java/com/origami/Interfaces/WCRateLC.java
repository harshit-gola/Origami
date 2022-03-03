package com.origami.Interfaces;

public class WCRateLC {
    
    private String classificationCode;
    private String classSuffDescCode;
    private String LCR;
    private String classificationMinPremiumAmt;
    
    public String getClassificationCode() {
        return classificationCode;
    }
    public void setClassificationCode(String classificationCode) {
        this.classificationCode = classificationCode;
    }
    public String getClassSuffDescCode() {
        return classSuffDescCode;
    }
    public void setClassSuffDescCode(String classSuffDescCode) {
        this.classSuffDescCode = classSuffDescCode;
    }
    public String getLCR() {
        return LCR;
    }
    public void setLCR(String lCR) {
        LCR = lCR;
    }
    public String getClassificationMinPremiumAmt() {
        return classificationMinPremiumAmt;
    }
    public void setClassificationMinPremiumAmt(String classificationMinPremiumAmt) {
        this.classificationMinPremiumAmt = classificationMinPremiumAmt;
    }

}
