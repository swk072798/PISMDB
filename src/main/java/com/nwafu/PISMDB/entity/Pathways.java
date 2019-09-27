package com.nwafu.PISMDB.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pathway")
public class Pathways {
    @Id
    private String PathwayID;
    private String PISMID;
    private String TargetID;
    private String PathwayName;
    private String PathwayInformation;

    public String getPathwayID() {
        return PathwayID;
    }

    public void setPathwayID(String pathwayID) {
        PathwayID = pathwayID;
    }

    public String getPISMID() {
        return PISMID;
    }

    public void setPISMID(String PISMID) {
        this.PISMID = PISMID;
    }

    public String getTargetID() {
        return TargetID;
    }

    public void setTargetID(String targetID) {
        TargetID = targetID;
    }

    public String getPathwayName() {
        return PathwayName;
    }

    public void setPathwayName(String pathwayName) {
        PathwayName = pathwayName;
    }

    public String getPathwayInformation() {
        return PathwayInformation;
    }

    public void setPathwayInformation(String pathwayInformation) {
        PathwayInformation = pathwayInformation;
    }
}
