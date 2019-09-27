package com.nwafu.PISMDB.service;

import com.nwafu.PISMDB.dao.CompoundsDao;
import com.nwafu.PISMDB.entity.Compounds;
import com.nwafu.PISMDB.entity.CompoundsBasicInformationBean;
import com.nwafu.PISMDB.entity.Pic;
import com.nwafu.PISMDB.entity.Pictures;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
public interface CompoundsService {
    public int getCompoundsCount();
    public List<Compounds> findAll();
    public List<Compounds> findById();
    public  List<CompoundsBasicInformationBean> FindBasicInformation();
    public  List<Compounds> FindPathway();
    public  List<Compounds> FindRelatedCompounds();

    public  List<Pictures> showPictureInformation();
    public  List<Pic> showPictures();
}
