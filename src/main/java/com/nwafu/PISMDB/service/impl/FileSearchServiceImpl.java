package com.nwafu.PISMDB.service.impl;

import com.nwafu.PISMDB.dao.CompoundsDao;
import com.nwafu.PISMDB.entity.CompoudsIdAndDescription;
import com.nwafu.PISMDB.service.FileSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @program: PISMDB
 * @description: 文件搜索部分
 * @author: liu qinchang
 * @create: 2019-09-30 16:39
 **/

@Service
public class FileSearchServiceImpl implements FileSearchService {

    private List<String> data_1 = null;
    private List<String> data_2 = null;

    @Autowired
    private CompoundsDao compoundsDao;

    private List<CompoudsIdAndDescription> idAndDescription= null;
    @Override
    public void selectIdAndDescription() {
        idAndDescription = compoundsDao.selectIdAndDescription();

    }

    @Override
    public String readFile(String file_path) {      //读取用户要搜索的文件,这时的文件已经转换为分子描述符文件
        String res = "";
        String temp = "";
        File file = new File(file_path);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while((temp = br.readLine()) != null) {
                res += temp;
            }
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res.split("\n")[1];
    }

    @Override
    public List<String> saveDataToArr_1(String str) {   //读入用户待查询的描述符文件到列表
        String[] a = str.split("\t");
        List<String> b = new ArrayList<>();
        int j = 0;
        for(int i = 0; i < a.length; i ++){
            data_1.set(j, a[i]);
            j++;
        }
        return b;
    }

    @Override
    public List<String> saveDataToArr_2(CompoudsIdAndDescription compoudsIdAndDescription) {     //读入单个数据库查询出的描述符
                //读取单个compoudsIdAndDescription中的数据到data_2中
        String[] str = compoudsIdAndDescription.getMocularDescription().split("\t");
        int j = 0;
        for(int i = 2; i < str.length; i++){
            data_2.set(j, str[i]);
            j++;
        }
        return data_2;
    }

    @Override
    public float caculate(List<String> a, List<String> b) {
        float sum_xiyi = 0;
        int i;
        for(i = 0;i<a.size();i++) {
            sum_xiyi += Float.parseFloat(a.get(i)) * Float.parseFloat(b.get(i));
        }

        float sum_xi2 = 0;
        for(i = 0;i<a.size();i++) {
            sum_xi2 += Float.parseFloat(a.get(i)) * Float.parseFloat(a.get(i));  //xi平方的和
        }

        float sum_yi2 = 0;
        for(i = 0;i<b.size();i++) {
            sum_yi2 += Float.parseFloat(b.get(i)) * Float.parseFloat(b.get(i));  //yi的平方和
        }

        float result = sum_xiyi/(sum_xi2 + sum_yi2 - sum_xiyi);
        return result;
    }

    @Override
    public void deleteZeroAndNa() {
        for(int i = 0;i<data_1.size();i++) {
            if(data_1.get(i).equals("0") || data_2.get(i).equals("0") ||
                    data_1.get(i).equals("na") || data_2.get(i).equals("na")
            ) {
                data_1.remove(i);
                data_2.remove(i);
                i--;
            }
        }
    }

    @Override
    public void fileSearch(String file_1, String file_1_lastname) {
        ///////////////////开始计时间////////////////////////
        long startTime = System.currentTimeMillis();
        /////////////
        List<String> PISMIDs = new ArrayList<>();
        String path = "D:\\IDEA_pro\\data_resource";
        selectIdAndDescription();
        saveDataToArr_1(readFile(path));     //读取用户上传文件到data_1
        for(CompoudsIdAndDescription ciad : idAndDescription){
            saveDataToArr_2(ciad);
            deleteZeroAndNa();
            Float result = caculate(data_1, data_2);
            if(result > 0.5){
                PISMIDs.add(ciad.getPISMID());
            }
        }



        /**
         * 这里还有点问题，最后应该返回给前端相应的数据的
         */
    }
}
