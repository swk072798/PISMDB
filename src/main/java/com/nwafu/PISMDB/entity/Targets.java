package com.nwafu.PISMDB.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "target")
@Data
public class Targets {
    @Id
    private String TargetID;
    @Column(name="uniprotid")
    private String UniprotID;
    @Column(name="proteinname")
    private String ProteinName;
    @Column
    private String Organisml;
    @Column
    private String Sequencel;
    @Column(name="pdbid")
    private String PDBID;
    @Column(name="pismid")
    private String PISMID;

//    @JsonProperty("TargetID")
//    public String getTargetID() {
//        return TargetID;
//    }
//    @JsonProperty("TargetID")
//    public void setTargetID(String targetID) {
//        TargetID = targetID;
//    }
//    @JsonProperty("UniprotID")
//    public String getUniprotID() {
//        return UniprotID;
//    }
//    @JsonProperty("UniprotID")
//    public void setUniprotID(String uniprotID) {
//        UniprotID = uniprotID;
//    }
//    @JsonProperty("proteinName")
//    public String getProteinName() {
//        return ProteinName;
//    }
//    @JsonProperty("proteinName")
//    public void setProteinName(String proteinName) {
//        ProteinName = proteinName;
//    }
//    @JsonProperty("organisml")
//    public String getOrganism1() {
//        return Organisml;
//    }
//    @JsonProperty("organisml")
//    public void setOrganism1(String organisml) {
//        Organisml = organisml;
//    }
//    @JsonProperty("sequencel")
//    public String getSequencel() {
//        return Sequencel;
//    }
//    @JsonProperty("sequencel")
//    public void setSequencel(String sequencel) {
//        Sequencel = sequencel;
//    }
//    @JsonProperty("PDBID")
//    public String getPDBID() {
//        return PDBID;
//    }
//    @JsonProperty("PDBID")
//    public void setPDBID(String PDBID) {
//        this.PDBID = PDBID;
//    }
//    @JsonProperty("PISMID")
//    public String getPISMID() {
//        return PISMID;
//    }
//    @JsonProperty("PISMID")
//    public void setPISMID(String PISMID) {
//        this.PISMID = PISMID;
//    }
}
