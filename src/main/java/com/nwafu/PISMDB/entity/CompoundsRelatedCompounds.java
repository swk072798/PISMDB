package com.nwafu.PISMDB.entity;

import lombok.Data;

import java.util.List;

@Data
public class CompoundsRelatedCompounds {
    private String PISMID;
    List<String> compoundsList;

    public CompoundsRelatedCompounds(){}
    public CompoundsRelatedCompounds(String PISMID,List<String> compoundsList){
        this.PISMID =  PISMID;
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
