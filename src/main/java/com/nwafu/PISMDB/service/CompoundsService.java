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
    int getCompoundsCount();
    List<Compounds> findAll();
    List<Compounds> findById();
    List<CompoundsBasicInformationBean> FindBasicInformation();
    List<Compounds> FindPathway();
    List<Compounds> FindRelatedCompounds();

    List<Pictures> showPictureInformation();
    List<Pic> showPictures();
}
