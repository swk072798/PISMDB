package com.nwafu.PISMDB.dao;

import com.nwafu.PISMDB.entity.Compounds;
import com.nwafu.PISMDB.entity.CompoundsBasicInformationBean;
import com.nwafu.PISMDB.entity.Pic;
import com.nwafu.PISMDB.entity.Pictures;
import com.nwafu.PISMDB.entity.CompoudsIdAndDescription;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompoundsDao {
    public int getCompoundsCount();

    List<Compounds> findAll();
    List<Compounds> findById();
    List<CompoundsBasicInformationBean> FindBasicInformation();
    List<Compounds> FindPathway();
    List<Compounds> FindRelatedCompounds();
    List<Compounds> FindSupportingInformation();

    List<Pictures> showPictureInformation();
    List<Pic> showPictures();
    List<CompoudsIdAndDescription> selectIdAndDescription();

    //public Compounds findById(String PISMID);
}

