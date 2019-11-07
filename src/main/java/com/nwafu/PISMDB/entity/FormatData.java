package com.nwafu.PISMDB.entity;

import lombok.Data;

@Data
public class FormatData {
    private String id;
    private String idLink;
    private String name;
    CompoundsBasic basic;
    CompoundsPathway pathway;
    CompoundsRelatedCompounds related;
    CompoundSupportingInformation supporting;

    @Override
    public String toString() {
        return "FormatData{" +
                "id='" + id + '\'' +
                ", idLink='" + idLink + '\'' +
                ", name='" + name + '\'' +
                ", basic=" + basic +
                ", pathway=" + pathway +
                ", related=" + related +
                ", supporting=" + supporting +
                '}';
    }
}
