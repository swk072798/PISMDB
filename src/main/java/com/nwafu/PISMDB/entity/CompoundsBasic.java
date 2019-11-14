package com.nwafu.PISMDB.entity;

import lombok.Data;

@Data
public class CompoundsBasic {
    private String pismid;
    private String iupacName;
    private String chemicalFormular;
    private String molecularWeight;
    private String alogP;
    private String smiles;

    @Override
    public String toString() {
        return "CompoundsBasic{" +
                "PISMID='" + pismid + '\'' +
                ", IUPAC_Name='" + iupacName + '\'' +
                ", ChemicalFormular='" + chemicalFormular + '\'' +
                ", MolecularWeight='" + molecularWeight + '\'' +
                ", AlogP='" + alogP + '\'' +
                ", Smiles='" + smiles + '\'' +
                '}';
    }
}
