package com.nwafu.PISMDB.service;

import com.nwafu.PISMDB.entity.CompoundsPathway;
import com.nwafu.PISMDB.entity.Pathways;

import java.util.List;

public interface PathwaysService {
    int getPathwaysCount();
    CompoundsPathway getPathwaysByPISMID(String pismid);
    List<Pathways> getPathwaysByTargetId(String targetId);
}