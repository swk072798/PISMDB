package com.nwafu.PISMDB.service;

import com.nwafu.PISMDB.entity.CompoudsIdAndDescription;
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
    void selectIdAndDescription();

    String readFile(String file_path);

    List<String> saveDataToArr_1(String str);

    List<String> saveDataToArr_2(CompoudsIdAndDescription compoudsIdAndDescription);

    float caculate(List<String> a,List<String> b);

    void deleteZeroAndNa();

    void fileSearch(String file_1,String file_1_lastname);

}
