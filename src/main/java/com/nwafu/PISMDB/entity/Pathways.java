package com.nwafu.PISMDB.entity;

import lombok.Data;

import javax.persistence.Column;
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
    @Column(name="targetid")
    private String TargetID;
    @Column(name="pathwayname")
    private String PathwayName;
    @Column(name="pathwayinformation")
    private String PathwayInformation;

}
