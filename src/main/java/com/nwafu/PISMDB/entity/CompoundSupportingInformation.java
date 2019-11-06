package com.nwafu.PISMDB.entity;

public class CompoundSupportingInformation {
    private String PISMID;
    private String Function;
    private String Mechanism;
    private String Phenotype;

    public String getPISMID() {
        return PISMID;
    }

    public void setPISMID(String PISMID) {
        this.PISMID = PISMID;
    }

    public String getFunction() {
        return Function;
    }

    public void setFunction(String function) {
        Function = function;
    }

    public String getMechanism() {
        return Mechanism;
    }

    public void setMechanism(String mechanism) {
        Mechanism = mechanism;
    }

    public String getPhenotype() {
        return Phenotype;
    }

    public void setPhenotype(String phenotype) {
        Phenotype = phenotype;
    }

    @Override
    public String toString() {
        return "CompoundSupportingInformation{" +
                "PISMID='" + PISMID + '\'' +
                ", Function='" + Function + '\'' +
                ", Mechanism='" + Mechanism + '\'' +
                ", Phenotype='" + Phenotype + '\'' +
                '}';
    }
}
