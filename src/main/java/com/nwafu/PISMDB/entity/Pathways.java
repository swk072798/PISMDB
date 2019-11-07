package com.nwafu.PISMDB.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pathway")
@Data
public class Pathways {
    @Id
    private String PathwayID;
    private String PISMID;
    private String TargetID;
    private String PathwayName;
    private String PathwayInformation;

}
