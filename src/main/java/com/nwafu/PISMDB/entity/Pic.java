package com.nwafu.PISMDB.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="pictures")
public class Pic {

    @Id
    @GeneratedValue
    int id;
    String desc;
    String url;
    //List<Pictures> list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /*public List<Pictures> getList() {
        return list;
    }

    public void setList(List<Pictures> list) {
        this.list = list;
    }*/
}
