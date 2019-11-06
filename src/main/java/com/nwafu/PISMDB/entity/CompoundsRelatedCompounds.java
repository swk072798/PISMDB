package com.nwafu.PISMDB.entity;

import java.util.List;

public class CompoundsRelatedCompounds {
    private String PISMID;
    List<String> compoundsList;

    public String getPISMID() {
        return PISMID;
    }

    public void setPISMID(String PISMID) {
        this.PISMID = PISMID;
    }

    public List<String> getCompoundsList() {
        return compoundsList;
    }

    public void setCompoundsList(List<String> compoundsList) {
        this.compoundsList = compoundsList;
    }

    @Override
    public String toString() {
        return "CompoundsRelatedCompounds{" +
                "PISMID='" + PISMID + '\'' +
                ", compoundsList=" + compoundsList +
                '}';
    }
}
