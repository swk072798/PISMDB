package com.nwafu.PISMDB.service;

import com.nwafu.PISMDB.entity.FormatData;
import com.nwafu.PISMDB.entity.SequenceSearchResult;
import com.nwafu.PISMDB.entity.Targets;

import java.io.File;
import java.util.List;


/**
* @Description: 序列搜索
* @Param:
* @return:
* @Author: liu qinchang
* @Date: 2019/9/29
*/
public interface BlastpSearchProteinService {
    //搜索之前，首先建立好blast库

    /**
     * 通过上传fasta文件查询
     * @param fastaFile
     * @return
     */
    List<FormatData<Targets>> fileSearchProtein(File fastaFile);

    List<FormatData<Targets>> seqSearchProtein(String seq);
}
