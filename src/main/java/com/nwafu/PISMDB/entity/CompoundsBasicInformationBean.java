package com.nwafu.PISMDB.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 */

@Data
@Entity
public class CompoundsBasicInformationBean {
    // 空返回
    //public static final CompoundsBasicInformationBean simple = new CompoundsBasicInformationBean();
    @Id
    private String PISMID;
    private String IUPAC_Name;
    private String ChemicalFormular;
    private String MolecularWeight;
    private String AlogP;
   // private String ThreeD_Structure;
    private String Smiles;
    private String ChemicalNames;
    private String Function;
    private String Mechanism;
    private String Phenotype;
//    private  String Address;

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
//        this.Address=Address;
    }
}
