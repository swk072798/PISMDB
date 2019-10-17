package com.nwafu.PISMDB.entity;

import lombok.Data;

/**
 * @program: PISMDB
 * @description: 存放compounds的id和分子描述符
 * @author: liu qinchang
 * @create: 2019-10-16 14:24
 **/

@Data
public class CompoudsIdAndDescription {
    private String PISMID;
    private String MocularDescription;
}
