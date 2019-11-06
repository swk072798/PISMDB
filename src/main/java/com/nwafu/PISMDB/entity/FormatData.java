package com.nwafu.PISMDB.entity;

public class FormatData {
    private String id;
    private String idLink;
    private String name;
    CompoundsBasic basic;
    CompoundsPathway pathway;
    CompoundsRelatedCompounds related;
    CompoundSupportingInformation supporting;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdLink() {
        return idLink;
    }

    public void setIdLink(String idLink) {
        this.idLink = idLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CompoundsBasic getBasic() {
        return basic;
    }

    public void setBasic(CompoundsBasic basic) {
        this.basic = basic;
    }

    public CompoundsPathway getPathway() {
        return pathway;
    }

    public void setPathway(CompoundsPathway pathway) {
        this.pathway = pathway;
    }

    public CompoundsRelatedCompounds getRelated() {
        return related;
    }

    public void setRelated(CompoundsRelatedCompounds related) {
        this.related = related;
    }

    public CompoundSupportingInformation getSupporting() {
        return supporting;
    }

    public void setSupporting(CompoundSupportingInformation supporting) {
        this.supporting = supporting;
    }

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
