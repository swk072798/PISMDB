package com.nwafu.PISMDB.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description:  文件搜索部分
* @Param:
* @return:
* @Author: liu qinchang
* @Date: 2019/9/30
*/

public interface FileSearchService {
    String readFile(String file_path);

    List<String> saveDataToArr(String str, String lastname);

    List<String> saveDataToArr(String str,String lastname,String sign);

    float caculate(List<String> a,List<String> b);

    void deleteZeroAndNa();

    void fileSearch(String file_1,String file_1_lastname);

}
