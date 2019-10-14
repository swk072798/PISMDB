package com.nwafu.PISMDB.service.impl;


import com.nwafu.PISMDB.dao.TargetsDao;
import com.nwafu.PISMDB.entity.SequenceSearchResult;
import com.nwafu.PISMDB.entity.Targets;
import com.nwafu.PISMDB.service.BlastpSearchProteinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
* @Description:
* @Param:
* @return:
* @Author: liu qinchang
* @Date: 2019/9/29
*/


@Service
@Slf4j
public class BlastpSearchProteinServiceImpl implements BlastpSearchProteinService {

    @Autowired
    TargetsDao targetsDao;
    /**
     * 通过上传文件进行序列查询
     * @param fastaFile
     * @return
     */
    @Override
    public List<SequenceSearchResult> fileSearchProtein(File fastaFile) {
        long nowTime = System.currentTimeMillis();
        File resultFile = new File("src/main/resources/seqsearch/result", fastaFile.getName().split("\\.")[0] +".txt");
        log.info("结果文件位置：{}",resultFile.getAbsolutePath());
        log.info("待查询的fasta文件位置,{}",fastaFile.getAbsolutePath());
        try {
            if(!resultFile.exists()){
                resultFile.createNewFile();
            }
            log.info("调用查找功能！！！");
            String command = "blastp -task blastp -query "+ fastaFile.getAbsolutePath() +" -db D:\\blast-2.4.0+\\bin\\pismdb -out "+ resultFile.getAbsolutePath() +" -matrix BLOSUM50 -outfmt \"7 bitscore evalue qcovs pident sacc stitle \" -num_threads 4";
            log.info("执行命令：{}" ,command);
            Process process = Runtime.getRuntime().exec(command);
            Thread.sleep(1000);     //这里留出1s的时间用于服务器执行命令行并生成result文件，不然可能会在文件内容写入之前读取，造成读空
        } catch (IOException e) {
            System.out.println("序列查询出错");
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList<String> txt = new ArrayList();
        try {
            FileReader fr = new FileReader(resultFile);
            BufferedReader br = new BufferedReader(fr);
            String str = new String();
            while((str = br.readLine()) != null){
                txt.add(str);
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("resultFile文件不存在");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("BufferedReader异常");
            e.printStackTrace();
        }
        System.out.println(txt);
        ArrayList<ArrayList<String>> similarityAndUniportID = new ArrayList<>();
        for(int i = 5; i < txt.size() - 1; i++){
            ArrayList<String> similarityAndId = new ArrayList<>();
            String[] str = txt.get(i).split("\t");  //通过TAB键分隔
            similarityAndId.add(str[3]);
            similarityAndId.add(str[4]);
            similarityAndUniportID.add(similarityAndId);
        }
        log.info("所有的相似度和uniportID列表:{}",similarityAndUniportID.toString());
        Double min_level = 0d;      //最小值，自己来设定
        for(ArrayList<String> al : similarityAndUniportID){
            if(Double.parseDouble(al.get(0)) < min_level){
                similarityAndUniportID.remove(al);
            }
        }
        if(similarityAndUniportID.size() > 0){
            similarityAndUniportID.sort(new Comparator<ArrayList<String>>() {
                @Override
                public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                    if(Double.parseDouble(o1.get(0)) > Double.parseDouble(o2.get(0))){
                        return 1;
                    }
                    else{
                        return 0;
                    }
                }
            });     //将剩余列表内容按照相似度从大到小排序
        }
        else{
            log.warn("没有满足条件的蛋白质???");
            return null;
        }
        List<SequenceSearchResult> sequenceSearchResults = new ArrayList<>();
        for(ArrayList<String> al : similarityAndUniportID){
            Targets targets = targetsDao.findTargetByUniportID(al.get(1));
            if(targets != null){
                SequenceSearchResult sequenceSearchResult = new SequenceSearchResult();
                sequenceSearchResult.setID("");
                sequenceSearchResult.setIdentity(String.format("%2f",Double.parseDouble(al.get(0)))+"%");       //保留两位小数
                sequenceSearchResult.setQueryCoverage("");
                sequenceSearchResult.setProtein(targets.getProteinName());
                sequenceSearchResult.setOrganisml(targets.getOrganism1());
                sequenceSearchResult.setUniportID(targets.getUniprotID());
                sequenceSearchResults.add(sequenceSearchResult);
            }
            else{
                log.error("{},数据库中查找失败!!!",al.get(1));
            }
        }
        return sequenceSearchResults;
    }

    @Override
    public List<SequenceSearchResult> seqSearchProtein(String sequence) {
        long nowTime = System.currentTimeMillis();
        String fileName = sequence.substring(0,13) + nowTime + ".fasta";
        File fastaFile = new File("src/main/resources/seqsearch/exchangedfasta/" + fileName);
        try {
            if(!fastaFile.exists()){
                fastaFile.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(fastaFile.getPath(),true);
            StringBuffer sb = new StringBuffer();
            sb.append("> \n");

            if(sequence.length() > 60){
                int i;
                for(i = 0; i < sequence.length() - 59; i += 60){
                    if(i + 59 < sequence.length()){
                        sb.append(sequence.substring(i, i + 59) + "\n");
                    }
                    else{
                        sb.append(sequence.substring(0,sequence.length())) ;
                    }
                }
            }
            else{
                sb.append(sequence);
            }
            fileWriter.write(sb.toString());
            fileWriter.flush();
            fileWriter.close();
            log.info("fasta文件转换完成！！！");
        } catch (IOException e) {
            e.printStackTrace();
        }       //将用户上传来的字符串转换成fasta文件的格式

        List<SequenceSearchResult> sequenceSearchResultList = fileSearchProtein(fastaFile);

        return sequenceSearchResultList;
    }

}
