package com.nwafu.PISMDB.entity;
import lombok.Data;
/**
 * @program: PISMDB
 * @description: 序列搜索的返回结果对象
 * @author: zxb
 * @create: 2019-09-29 11:44
 **/

@Data
public class SequenceSearchResult {
    private String ID;
    private String identity;
    private String queryCoverage;
    private String protein;
    private String organisml;
    private String uniportID;
    private String gene;
    private String function;
}
