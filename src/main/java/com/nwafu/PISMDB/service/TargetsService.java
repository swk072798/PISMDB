package com.nwafu.PISMDB.service;

import com.nwafu.PISMDB.entity.*;

import java.util.List;

public interface TargetsService {
    int getTargetsCount();
    List<Targets> findTargets();
    List<FormatData<Targets>> findTargetsFormat();
    Targets findTargetByUniportID(String UniprotID);
}
