package com.nwafu.PISMDB.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @program: PISMDB
 * @description: 存放compounds的id和分子描述符
 * @author: liu qinchang
 * @create: 2019-10-16 14:24
 **/

@Data
public class CompoudsIdAndDescription {
    @Id
    private String pismid;
    private String mocularDescription;
}
