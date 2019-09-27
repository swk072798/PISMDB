package com.nwafu.PISMDB.dao;

import com.nwafu.PISMDB.entity.Compounds;
import com.nwafu.PISMDB.entity.CompoundsBasicInformationBean;
import com.nwafu.PISMDB.entity.Pic;
import com.nwafu.PISMDB.entity.Pictures;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompoundsDao {
    public int getCompoundsCount();

    public List<Compounds> findAll();
    public  List<Compounds> findById();
    public  List<CompoundsBasicInformationBean> FindBasicInformation();
    public  List<Compounds> FindPathway();
    public  List<Compounds> FindRelatedCompounds();
    public  List<Compounds> FindSupportingInformation();

    public  List<Pictures> showPictureInformation();
    public  List<Pic> showPictures();

    //public Compounds findById(String PISMID);
}

