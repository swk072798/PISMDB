package com.nwafu.PISMDB.service;

import com.nwafu.PISMDB.entity.*;

import java.util.List;

public interface TargetsService {
    int getTargetsCount();
    List<Targets> findTargetById();
    Targets findTargetByUniportID(String UniprotID);
}
