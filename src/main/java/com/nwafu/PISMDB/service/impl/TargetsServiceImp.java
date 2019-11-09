package com.nwafu.PISMDB.service.impl;

import com.nwafu.PISMDB.dao.TargetsDao;
import com.nwafu.PISMDB.entity.Targets;
import com.nwafu.PISMDB.service.TargetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TargetsServiceImp implements TargetsService {
    @Autowired
    private TargetsDao targetsDao;

    @Override
    public int getTargetsCount() {
        return targetsDao.getTargetsCount();
    }

    @Override
    public List<Targets> findTargetById()
    {
        return targetsDao.findTargetById();
    }

    @Override
    public Targets findTargetByUniportID(String UniprotID){
        return targetsDao.findTargetByUniportID(UniprotID);
    }
}

