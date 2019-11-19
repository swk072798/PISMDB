package com.nwafu.PISMDB.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "compound")
public class Compounds {

    @Id
    private String pismid;
    @Column(name="targetid")
    private String targetID;
    @Column(name="pathwayid")
    private String pathwayID;
    @Column
    private String version;
    @Column(name="creationdate")
    private String creationDate;
    @Column(name="updatedate")
    private String updateDate;
    @Column(name="chemicalnames")
    private String chemicalNames;
    @Column
    private String iupacName;
    @Column(name="othernames")
    private String otherNames;
    @Column(name="chemicalformular")
    private String chemicalFormular;
    @Column(name="molecularweight")
    private String molecularWeight;
    @Column
    private String alogP;
    @Column
    private String threeD_Structure;
    @Column
    private String structure;
    @Column
    private String smiles;
    @Column
    private String function;
    @Column
    private String mechanism;
    @Column
    private String phenotype;
    @Column(name="groupdescription")
    private String groupDescription;
    @Column(name="structureactivityrelationship")
    private String structureActivityRelationship;
    @Column(name="structuralsimilarity")
    private String structuralSimilarity;
//    @Column
//    private String Address;
//    @Column
//    private String updatedate2;
//    @Column
//    private String MocularDescription;

    @Override
    public String toString() {
        return "Compounds{" +
                "PISMID='" + pismid + '\'' +
                ", TargetID='" + targetID + '\'' +
                ", PathwayID='" + pathwayID + '\'' +
                ", Version='" + version + '\'' +
                ", CreationDate='" + creationDate + '\'' +
                ", UpdateDate2='" + updateDate + '\'' +
                ", ChemicalNames='" + chemicalNames + '\'' +
                ", IUPAC_Name='" + iupacName + '\'' +
                ", OtherNames='" + otherNames + '\'' +
                ", ChemicalFormular='" + chemicalFormular + '\'' +
                ", MolecularWeight='" + molecularWeight + '\'' +
                ", AlogP='" + alogP + '\'' +
                ", ThreeD_Structure='" + threeD_Structure + '\'' +
                ", Structure='" + structure + '\'' +
                ", Smiles='" + smiles + '\'' +
                ", Function='" + function + '\'' +
                ", Mechanism='" + mechanism + '\'' +
                ", Phenotype='" + phenotype + '\'' +
                ", GroupDescription='" + groupDescription + '\'' +
                ", StructureActivityRelationship='" + structureActivityRelationship + '\'' +
                ", StructuralSimilarity='" + structuralSimilarity + '\'' +
//                ", Address='" + Address + '\'' +
                '}';
    }


    //    @JSONField(format = "yyyy-MM-dd HH:mm")
//    private Date createTime;
//
//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
}
