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
    @Column(name="updatedate2")
    private String UpdateDate2;
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
    @Column
    private String Address;
    @Column
    private String MocularDescription;
    @JsonProperty("PISMID")
    public String getPISMID() {
        return PISMID;
    }
    @JsonProperty("PISMID")
    public void setPISMID(String pismid) {
        PISMID = pismid;
    }

    @JsonProperty("TargetID")
    public String getTargetID() {
        return TargetID;
    }
    @JsonProperty("PathwayID")
    public String getPathwayID() {
        return PathwayID;
    }
    @JsonProperty("PathwayID")
    public void setPathwayID(String pathwayID) {
        PathwayID = pathwayID;
    }
    @JsonProperty("Version")
    public String getVersion() {
        return Version;
    }
    @JsonProperty("Version")
    public void setVersion(String version) {
        Version = version;
    }
    @JsonProperty("CreationDate")
    public String getCreationDate() {
        return CreationDate;
    }
    @JsonProperty("CreationDate")
    public void setCreationDate(String creationDate) {
        CreationDate = creationDate;
    }
    @JsonProperty("UpdateDate2")
    public String getUpdateDate2() {
        return UpdateDate2;
    }
    @JsonProperty("UpdateDate2")
    public void setUpdateDate2(String updateDate2) {
        UpdateDate2 = updateDate2;
    }
    @JsonProperty("TargetID")
    public void setTargetID(String targetID) {
        TargetID = targetID;
    }
    @JsonProperty("ChemicalNames")
    public String getChemicalNames() {
        return ChemicalNames;
    }
    @JsonProperty("ChemicalNames")
    public void setChemicalNames(String chemicalNames) {
        ChemicalNames = chemicalNames;
    }
    @JsonProperty("IUPAC_Name")
    public String getIUPAC_Name() {
        return IUPAC_Name;
    }
    @JsonProperty("IUPAC_Name")
    public void setIUPAC_Name(String IUPAC_Name) {
        this.IUPAC_Name = IUPAC_Name;
    }
    @JsonProperty("OtherNames")
    public String getOtherNames() {
        return OtherNames;
    }
    @JsonProperty("OtherNames")
    public void setOtherNames(String otherNames) {
        OtherNames = otherNames;
    }
    @JsonProperty("ChemicalFormular")
    public String getChemicalFormular() {
        return ChemicalFormular;
    }
    @JsonProperty("ChemicalFormular")
    public void setChemicalFormular(String chemicalFormular) {
        ChemicalFormular = chemicalFormular;
    }
    @JsonProperty("MolecularWeight")
    public String getMolecularWeight() {
        return MolecularWeight;
    }
    @JsonProperty("MolecularWeight")
    public void setMolecularWeight(String molecularWeight) {
        MolecularWeight = molecularWeight;
    }
    @JsonProperty("AlogP")
    public String getAlogP() {
        return AlogP;
    }
    @JsonProperty("AlogP")
    public void setAlogP(String alogP) {
        AlogP = alogP;
    }
    @JsonProperty("ThreeD_Structure")
    public String getThreeD_Structure() {
        return ThreeD_Structure;
    }
    @JsonProperty("ThreeD_Structure")
    public void setThreeD_Structure(String threeD_Structure) {
        ThreeD_Structure = threeD_Structure;
    }
    @JsonProperty("Structure")
    public String getStructure() {
        return Structure;
    }
    @JsonProperty("Structure")
    public void setStructure(String structure) {
        Structure = structure;
    }
    @JsonProperty("Smiles")
    public String getSmiles() {
        return Smiles;
    }
    @JsonProperty("Smiles")
    public void setSmiles(String smiles) {
        Smiles = smiles;
    }
    @JsonProperty("Function")
    public String getFunction() {
        return Function;
    }
    @JsonProperty("Function")
    public void setFunction(String function) {
        Function = function;
    }
    @JsonProperty("Mechanism")
    public String getMechanism() {
        return Mechanism;
    }
    @JsonProperty("Mechanism")
    public void setMechanism(String mechanism) {
        Mechanism = mechanism;
    }
    @JsonProperty("Phenotype")
    public String getPhenotype() {
        return Phenotype;
    }
    @JsonProperty("Phenotype")
    public void setPhenotype(String phenotype) {
        Phenotype = phenotype;
    }
    @JsonProperty("GroupDescription")
    public String getGroupDescription() {
        return GroupDescription;
    }
    @JsonProperty("GroupDescription")
    public void setGroupDescription(String groupDescription) {
        GroupDescription = groupDescription;
    }
    @JsonProperty("StructureActivityRelationship")
    public String getStructureActivityRelationship() {
        return StructureActivityRelationship;
    }
    @JsonProperty("StructureActivityRelationship")
    public void setStructureActivityRelationship(String structureActivityRelationship) {
        StructureActivityRelationship = structureActivityRelationship;
    }
    @JsonProperty("StructuralSimilarity")
    public String getStructuralSimilarity() {
        return StructuralSimilarity;
    }
    @JsonProperty("StructuralSimilarity")
    public void setStructuralSimilarity(String structuralSimilarity) {
        StructuralSimilarity = structuralSimilarity;
    }
    @JsonProperty("Address")
    public String getAddress(){return Address;}
    @JsonProperty("Address")
    public void setAddress(String address){Address=address;}

    @Override
    public String toString() {
        return "Compounds{" +
                "PISMID='" + PISMID + '\'' +
                ", TargetID='" + TargetID + '\'' +
                ", PathwayID='" + PathwayID + '\'' +
                ", Version='" + Version + '\'' +
                ", CreationDate='" + CreationDate + '\'' +
                ", UpdateDate2='" + UpdateDate2 + '\'' +
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
                ", Address='" + Address + '\'' +
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
