package com.nwafu.PISMDB.service;

import com.nwafu.PISMDB.entity.*;

import java.util.List;

public interface TargetsService {
    public int getTargetsCount();
    public List<Targets> findTargetById();
}
