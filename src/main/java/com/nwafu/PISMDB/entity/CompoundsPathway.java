package com.nwafu.PISMDB.entity;

public class CompoundsPathway {
    private String PISMID;
    private String PathwayID;

    public String getPISMID() {
        return PISMID;
    }

    public void setPISMID(String PISMID) {
        this.PISMID = PISMID;
    }

    public String getPathwayID() {
        return PathwayID;
    }

    public void setPathwayID(String pathwayID) {
        PathwayID = pathwayID;
    }

    @Override
    public String toString() {
        return "CompoundsPathway{" +
                "PISMID='" + PISMID + '\'' +
                ", PathwayID='" + PathwayID + '\'' +
                '}';
    }
}
