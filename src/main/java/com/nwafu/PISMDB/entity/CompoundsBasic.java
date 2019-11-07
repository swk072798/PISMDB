package com.nwafu.PISMDB.entity;

import lombok.Data;

@Data
public class CompoundsBasic {
    private String PISMID;
    private String IUPAC_Name;
    private String ChemicalFormular;
    private String MolecularWeight;
    private String AlogP;
    private String Smiles;

    @Override
    public String toString() {
        return "CompoundsBasic{" +
                "PISMID='" + PISMID + '\'' +
                ", IUPAC_Name='" + IUPAC_Name + '\'' +
                ", ChemicalFormular='" + ChemicalFormular + '\'' +
                ", MolecularWeight='" + MolecularWeight + '\'' +
                ", AlogP='" + AlogP + '\'' +
                ", Smiles='" + Smiles + '\'' +
                '}';
    }
}
