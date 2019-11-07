package com.nwafu.PISMDB.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="pictures")
@Data
public class Pic {

    @Id
    @GeneratedValue
    int id;
    String desc;
    String url;
    //List<Pictures> list;

}
