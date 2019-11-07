package com.nwafu.PISMDB.entity;

import lombok.Data;

@Data
public class CompoundsPathway {
    private String PISMID;
    private String PathwayID;

    @Override
    public String toString() {
        return "CompoundsPathway{" +
                "PISMID='" + PISMID + '\'' +
                ", PathwayID='" + PathwayID + '\'' +
                '}';
    }
}
