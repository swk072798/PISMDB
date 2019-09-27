package com.nwafu.PISMDB.service;

import com.nwafu.PISMDB.dao.CompoundsDao;
import com.nwafu.PISMDB.entity.Compounds;
import com.nwafu.PISMDB.entity.CompoundsBasicInformationBean;
import com.nwafu.PISMDB.entity.Pic;
import com.nwafu.PISMDB.entity.Pictures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompoundsServiceImp implements CompoundsService {
    @Autowired
    private CompoundsDao compoundsDao;
    private Compounds compounds;
    //
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
    public  List<CompoundsBasicInformationBean> FindBasicInformation(){
        return compoundsDao.FindBasicInformation();
    }

    @Override
    public  List<Compounds> FindPathway(){
        return  compoundsDao.FindPathway();
    }
    @Override
    public  List<Compounds> FindRelatedCompounds(){
        return  compoundsDao.FindRelatedCompounds();
    }

    @Override
    public List<Pictures> showPictureInformation() {
        return compoundsDao.showPictureInformation();
    }

    @Override
    public List<Pic> showPictures() {
        return compoundsDao.showPictures();
    }


}