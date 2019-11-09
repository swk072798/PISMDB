package com.nwafu.PISMDB.service;

import com.nwafu.PISMDB.entity.CompoundsPathway;
import com.nwafu.PISMDB.entity.Pic;
import com.nwafu.PISMDB.entity.Pictures;
import com.nwafu.PISMDB.entity.Pathways;


import java.util.List;

public interface PathwaysService {
    int getPathwaysCount();
    int getPicturesCount();
    List<Pictures> showPictureInformation(int picturesid);
    List<Pic> showPictures();
    CompoundsPathway getPathwaysByPISMID(String pismid);
    List<Pathways> getPathwaysByTargetId(String targetId);
}