package com.nwafu.PISMDB.dao;

import com.nwafu.PISMDB.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompoundsDao {
    public int getCompoundsCount();

    List<Compounds> findAll();
    List<Compounds> findById();
    List<CompoundsBasicInformationBean> FindBasicInformation();
    List<CompoundsBasic> FindBasic();
    List<CompoundsPathway> FindPathway();
    List<CompoundsRelatedCompounds> FindRelatedCompounds();
    List<CompoundSupportingInformation> FindSupportingInformation();
    List<String> findRelatedById(@Param("Pismid") String Pismid);
    List<Pictures> showPictureInformation();
    List<Pic> showPictures();
    List<CompoudsIdAndDescription> selectIdAndDescription();
    Compounds findByPISMID(String pismid);

    //public Compounds findById(String PISMID);
}

