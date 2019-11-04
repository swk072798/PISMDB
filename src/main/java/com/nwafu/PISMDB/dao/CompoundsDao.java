package com.nwafu.PISMDB.dao;

import com.nwafu.PISMDB.entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompoundsDao {
    public int getCompoundsCount();

    List<Compounds> findAll();
    List<Compounds> findById();
    List<CompoundsBasicInformationBean> FindBasicInformation();
    List<CompoundsPathway> FindPathway();
    List<CompoundsRelatedCompounds> FindRelatedCompounds();
    List<CompoundSupportingInformation> FindSupportingInformation();

    List<Pictures> showPictureInformation();
    List<Pic> showPictures();
    List<CompoudsIdAndDescription> selectIdAndDescription();

    //public Compounds findById(String PISMID);
}

