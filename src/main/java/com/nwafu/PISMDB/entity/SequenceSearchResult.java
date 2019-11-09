package com.nwafu.PISMDB.entity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @program: PISMDB
 * @description: 序列搜索的返回结果对象
 * @author: liu qinchang
 * @create: 2019-09-29 11:44
 **/

@Data
public class SequenceSearchResult {
    @Id
    private String ID;
    private String identity;
    private String queryCoverage;
    private String protein;
    private String organisml;
    private String uniportID;
    private String gene;
    private String function;
}
