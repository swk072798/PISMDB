package com.nwafu.PISMDB.entity;


import lombok.Data;

@Data
public class CompoundSupportingInformation {
    private String PISMID;
    private String Function;
    private String Mechanism;
    private String Phenotype;

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
