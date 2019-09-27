package com.nwafu.PISMDB.service;

import com.nwafu.PISMDB.dao.PathwaysDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PathwaysServiceImp implements PathwaysService {
    @Autowired
    private PathwaysDao pathwaysDao;

    @Override
    public int getPathwaysCount() {
        return pathwaysDao.getPathwaysCount();
    }
}
