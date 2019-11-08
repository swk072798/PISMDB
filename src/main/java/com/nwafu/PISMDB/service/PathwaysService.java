package com.nwafu.PISMDB.service;

import com.nwafu.PISMDB.entity.CompoundsPathway;

public interface PathwaysService {
    int getPathwaysCount();
    CompoundsPathway getPathwaysByPISMID(String pismid);
}
