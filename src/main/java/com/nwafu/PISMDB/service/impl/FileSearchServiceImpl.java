package com.nwafu.PISMDB.service.impl;

import com.nwafu.PISMDB.service.FileSearchService;
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
    @Override
    public String readFile(String file_path) {
        String res = "";
        String temp = "";
        File file = new File(file_path);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while((temp = br.readLine()) != null) {
                res += temp;
//				System.out.println(temp);
            }
//            System.out.println(res);
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public List<String> saveDataToArr(String str, String lastname) {
        String [] a = str.split("	");
        List<String> b = new ArrayList<>();
        boolean flag = false;

        for(int i = 0;i<a.length;i++) {
            if(a[i].equals("A1_0(C" + lastname +")")) {
                flag = true;
                i++;
            }
//			System.out.print(i);
            if(flag == true) {
                b.add(a[i]);
//				System.out.print("1");
            }
        }

        return b;
    }

    @Override
    public List<String> saveDataToArr(String str, String lastname, String sign) {
        //以字符串sign为数据的开始标志符，将数据保存到数组，用来获取.smile中的数据
        String [] a = str.split("	");
        List<String> b = new ArrayList<>();
        boolean flag = false;

        for(int i = 0;i<a.length;i++) {
            if(a[i].equals(sign)) {
                flag = true;
                i++;
            }

//			System.out.print(i);
            if(flag == true) {
                b.add(a[i]);
//				System.out.print("1");
            }
        }

        return b;
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
        //        String file_1 = "C:\\Users\\47405\\Desktop\\20R.20R";
//        String file_2 = "C:\\Users\\47405\\Desktop\\20S.20S";
//            String file_1_lastname = "20R";   //更换文件时记得修改,作为mol2文件数据开始的标志符
//        String file_2_lastname = "20S";

        ///////////////////开始计时间////////////////////////
        long startTime = System.currentTimeMillis();
        /////////////
        String path = "D:\\IDEA_pro\\data_resource";
        File file = new File(path);
        File [] fs =  file.listFiles();
        System.out.println("文件个数："+fs.length);

        ArrayList<Double> al_1 = new ArrayList<>();
        ArrayList<String> al_path = new ArrayList<>();

        for(File f : fs){
            String res1 = readFile(file_1);
            String res2 = readFile(f.getPath());

            String file_2_lastname = f.getName().split("\\.")[0];
            data_1 = saveDataToArr(res1,file_1_lastname);
            data_2 = saveDataToArr(res2,file_2_lastname);
            deleteZeroAndNa();

            double result = caculate(data_1, data_2);
            if(result >0.5){        //筛选出评分大于0.5的
                al_1.add(result);
                al_path.add(f.getPath());
            }
        }

        System.out.println(al_1.toString());
        System.out.println(al_path.toString());

        long endTime = System.currentTimeMillis();
        System.out.println("计算时间为 ： " + (endTime - startTime));
        /**
         * 这里还有点问题，最后应该返回给前端相应的数据的
         */
    }
}
