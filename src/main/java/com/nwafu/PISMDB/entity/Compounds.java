package com.nwafu.PISMDB.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "compound")
@Data
public class Compounds {

    @Id
    private String PISMID;
    @Column(name="targetid")
    private String TargetID;
    @Column(name="pathwayid")
    private String PathwayID;
    @Column
    private String Version;
    @Column(name="creationdate")
    private String CreationDate;
    @Column(name="updatedate")
    private String UpdateDate;
    @Column(name="chemicalnames")
    private String ChemicalNames;
    @Column
    private String IUPAC_Name;
    @Column(name="othernames")
    private String OtherNames;
    @Column(name="chemicalformular")
    private String ChemicalFormular;
    @Column(name="molecularweight")
    private String MolecularWeight;
    @Column
    private String AlogP;
    @Column
    private String ThreeD_Structure;
    @Column
    private String Structure;
    @Column
    private String Smiles;
    @Column
    private String Function;
    @Column
    private String Mechanism;
    @Column
    private String Phenotype;
    @Column(name="groupdescription")
    private String GroupDescription;
    @Column(name="structureactivityrelationship")
    private String StructureActivityRelationship;
    @Column(name="structuralsimilarity")
    private String StructuralSimilarity;
//    @Column
//    private String Address;
//    @Column
//    private String updatedate2;
//    @Column
//    private String MocularDescription;

    @Override
    public String toString() {
        return "Compounds{" +
                "PISMID='" + PISMID + '\'' +
                ", TargetID='" + TargetID + '\'' +
                ", PathwayID='" + PathwayID + '\'' +
                ", Version='" + Version + '\'' +
                ", CreationDate='" + CreationDate + '\'' +
                ", UpdateDate2='" + UpdateDate + '\'' +
                ", ChemicalNames='" + ChemicalNames + '\'' +
                ", IUPAC_Name='" + IUPAC_Name + '\'' +
                ", OtherNames='" + OtherNames + '\'' +
                ", ChemicalFormular='" + ChemicalFormular + '\'' +
                ", MolecularWeight='" + MolecularWeight + '\'' +
                ", AlogP='" + AlogP + '\'' +
                ", ThreeD_Structure='" + ThreeD_Structure + '\'' +
                ", Structure='" + Structure + '\'' +
                ", Smiles='" + Smiles + '\'' +
                ", Function='" + Function + '\'' +
                ", Mechanism='" + Mechanism + '\'' +
                ", Phenotype='" + Phenotype + '\'' +
                ", GroupDescription='" + GroupDescription + '\'' +
                ", StructureActivityRelationship='" + StructureActivityRelationship + '\'' +
                ", StructuralSimilarity='" + StructuralSimilarity + '\'' +
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
