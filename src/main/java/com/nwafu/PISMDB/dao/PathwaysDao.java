package com.nwafu.PISMDB.dao;

import com.nwafu.PISMDB.entity.CompoundsPathway;
import org.springframework.stereotype.Repository;

@Repository
public interface PathwaysDao {
    int getPathwaysCount();
    CompoundsPathway getPathwaysByPISMID(String pismid);
}
