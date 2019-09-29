package com.nwafu.PISMDB.dao;

import com.nwafu.PISMDB.entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TargetsDao {
    int getTargetsCount();
    List<Targets> findTargetById();
    Targets findTargetByUniprotID(String UniprotID);
}
