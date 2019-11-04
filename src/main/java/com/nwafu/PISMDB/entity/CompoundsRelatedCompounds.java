package com.nwafu.PISMDB.entity;

public class CompoundsRelatedCompounds {
    private String PISMID;
    private String IUPAC_Name;
    private String ChemicalFormular;
    private String AlogP;
    private String Smiles;

    public String getPISMID() {
        return PISMID;
    }

    public void setPISMID(String PISMID) {
        this.PISMID = PISMID;
    }

    public String getIUPAC_Name() {
        return IUPAC_Name;
    }

    public void setIUPAC_Name(String IUPAC_Name) {
        this.IUPAC_Name = IUPAC_Name;
    }

    public String getChemicalFormular() {
        return ChemicalFormular;
    }

    public void setChemicalFormular(String chemicalFormular) {
        ChemicalFormular = chemicalFormular;
    }

    public String getAlogP() {
        return AlogP;
    }

    public void setAlogP(String alogP) {
        AlogP = alogP;
    }

    public String getSmiles() {
        return Smiles;
    }

    public void setSmiles(String smiles) {
        Smiles = smiles;
    }

    @Override
    public String toString() {
        return "CompoundsPathway{" +
                "PISMID='" + PISMID + '\'' +
                ", IUPAC_Name='" + IUPAC_Name + '\'' +
                ", ChemicalFormular='" + ChemicalFormular + '\'' +
                ", AlogP='" + AlogP + '\'' +
                ", Smiles='" + Smiles + '\'' +
                '}';
    }
}
