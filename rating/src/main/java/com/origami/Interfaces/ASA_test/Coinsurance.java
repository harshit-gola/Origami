package com.origami.Interfaces.ASA_test;

public class Coinsurance {
    private String type;
    private String hazardGrp;
    private String amount;
    private String percent;
    
    public String getPercent() {
        return percent;
    }
    public void setPercent(String percent) {
        this.percent = percent;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getHazardGrp() {
        return hazardGrp;
    }
    public void setHazardGrp(String hazardGrp) {
        this.hazardGrp = hazardGrp;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
}
