package com.nwafu.PISMDB.dao;

import com.nwafu.PISMDB.entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TargetsDao {
    public int getTargetsCount();
  public List<Targets> findTargetById();
}
