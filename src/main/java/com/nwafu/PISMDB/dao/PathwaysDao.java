package com.nwafu.PISMDB.dao;

import com.nwafu.PISMDB.entity.CompoundsPathway;
import com.nwafu.PISMDB.entity.Pathways;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PathwaysDao {
    int getPathwaysCount();
    CompoundsPathway getPathwaysByPISMID(String pismid);
    List<Pathways> getPathwaysByTargetId(String targetId);
}
