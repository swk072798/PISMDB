package com.nwafu.PISMDB.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 向前端返回数据的基类
 *
 * @param <T>
 */

public class CompoundsBasicInformationBean {
    // 空返回
    //public static final CompoundsBasicInformationBean simple = new CompoundsBasicInformationBean();
    private String PISMID;
    private String IUPAC_Name;
    private String ChemicalFormular;
   // private String MolecularWeight;
    private String AlogP;
   // private String ThreeD_Structure;
    private String Smiles;
    private String ChemicalNames;
    private String Function;
    private String Mechanism;
    private String Phenotype;
    private  String Address;
    @JsonProperty("PISMID")
    public String getPISMID() {
        return PISMID;
    }

    public void setPISMID(String PISMID) {
        this.PISMID = PISMID;
    }
    @JsonProperty("IUPAC_Name")
    public String getIUPAC_Name() {
        return IUPAC_Name;
    }

    public void setIUPAC_Name(String IUPAC_Name) {
        this.IUPAC_Name = IUPAC_Name;
    }
    @JsonProperty("ChemicalFormular")
    public String getChemicalFormular() {
        return ChemicalFormular;
    }

    public void setChemicalFormular(String ChemicalFormular) {
        this.ChemicalFormular = ChemicalFormular;
    }
    @JsonProperty("AlogP")
    public String getAlogP() {
        return AlogP;
    }

    public void setAlogP(String AlogP) {
        this.AlogP = AlogP;
    }
    @JsonProperty("Smiles")
    public String getSmiles() {
        return Smiles;
    }

    public void setSmiles(String Smiles) {
        this.Smiles = Smiles;
    }
    @JsonProperty("ChemicalNames")
    public String getChemicalNames() {
        return ChemicalNames;
    }

    public void setChemicalNames(String ChemicalNames) {
        this.ChemicalNames = ChemicalNames;
    }

    @JsonProperty("Function")
    public String getFunction() {
        return Function;
    }

    public void setFunction(String function) {
        Function = function;
    }
    @JsonProperty("Mechanism")
    public String getMechanism() {
        return Mechanism;
    }

    public void setMechanism(String mechanism) {
        Mechanism = mechanism;
    }
    @JsonProperty("Phenotype")
    public String getPhenotype() {
        return Phenotype;
    }
    public void setPhenotype(String phenotype) {
        Phenotype = phenotype;
    }
    @JsonProperty("Address")
    public String getAddress(){return Address;}

    public void setAddress(String address){Address=address;}



    public CompoundsBasicInformationBean(String PISMID, String IUPAC_Name, String ChemicalFormular, String AlogP,
                                         String Smiles,String chemicalNames,String Function,String Mechanism ,String Phenotype,
                                         String Address)  {
        this.PISMID = PISMID;
        this.IUPAC_Name = IUPAC_Name;
        this.ChemicalFormular = ChemicalFormular;
       // this.MolecularWeight = MolecularWeight;
        this.AlogP = AlogP;
        //this.ThreeD_Structure = ThreeD_Structure;
        this.Smiles = Smiles;
        this.ChemicalNames=chemicalNames;
        this.Function=Function;
        this.Mechanism=Mechanism;
        this.Phenotype=Phenotype;
        this.Address=Address;
    }
}
