package com.nwafu.PISMDB.entity;

import lombok.Data;

@Data
public class FormatData<T> {
    private String id;
    private String idLink;
    private String name;
    private String imgurl;
    private T basic;    //这里根据不同的分子显示不同的信息
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
