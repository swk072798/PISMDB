package com.nwafu.PISMDB.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reference")
@Data
public class Reference {
    @Id
    private String referenceID;
    @Column(name="referencename")
    private String referenceName;
}
