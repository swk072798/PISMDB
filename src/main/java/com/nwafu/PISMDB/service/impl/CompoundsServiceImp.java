package com.nwafu.PISMDB.service.impl;

import com.nwafu.PISMDB.dao.CompoundsDao;
import com.nwafu.PISMDB.entity.*;
import com.nwafu.PISMDB.service.CompoundsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompoundsServiceImp implements CompoundsService {
    private Compounds compounds;

    @Autowired
    private CompoundsDao compoundsDao;

    @Override
    public List<Compounds> findById() {
        return compoundsDao.findById();
    }
    @Override
    public int getCompoundsCount() {
        return compoundsDao.getCompoundsCount();
    }
    @Override
    public  List<Compounds> findAll(){
        return compoundsDao.findAll();
    }

    @Override
    public  List<CompoundsBasic> FindBasic(){
        return compoundsDao.FindBasic();
    }

    @Override
    public  List<CompoundsBasicInformationBean> FindBasicInformation(){
        return compoundsDao.FindBasicInformation();
    }

    @Override
    public  List<CompoundsPathway> FindPathway(){
        return  compoundsDao.FindPathway();
    }
    @Override
    public  List<CompoundsRelatedCompounds> FindRelatedCompounds(){
        return  compoundsDao.FindRelatedCompounds();
    }

    @Override
    public List<CompoundSupportingInformation> FindSupportingInformation(){
        return compoundsDao.FindSupportingInformation();
    }
    @Override
    public List<Pictures> showPictureInformation() {
        return compoundsDao.showPictureInformation();
    }

    @Override
    public List<Pic> showPictures() {
        return compoundsDao.showPictures();
    }

    @Override
    public List<String> findRelatedById(String pismid){
        return compoundsDao.findRelatedById(pismid);
    }

}