package com.nwafu.PISMDB.controller;

import com.nwafu.PISMDB.entity.SequenceSearchResult;
import com.nwafu.PISMDB.entity.Targets;
import com.nwafu.PISMDB.service.BlastpSearchProteinService;
import com.nwafu.PISMDB.service.TargetsService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.*;
import java.util.List;

@RestController
@Slf4j
//@RequestMapping("/data")
public class TargetsController {
    @Autowired
    private TargetsService targetsService;
    @Autowired
    private BlastpSearchProteinService blastpSearchProteinService;
    
    
//    @RequestMapping("/browse-T")
//    @ResponseBody
//    //@Test
//    public List<Targets> showTargets1() {
//        List<Targets> list = targetsService.findTargetById();
//        System.out.println("data数据" + list.size());
//
//        return list;
//    }
    @ApiOperation(value = "跳转到Target页面", notes = "跳转到Target页面")
    @GetMapping("/Browse_T")
    public String Browse_T() {
        return "browse-Target";
    }

    @ApiOperation(value = "蛋白质json数据", notes = "从数据库中获取的蛋白质的json数据")
    @GetMapping("/browse-T")
    @ResponseBody
    public List<Targets> showTargets1() {
        List<Targets> list = targetsService.findTargetById();
        System.out.println("data数据" + list.size());

        return list;
    }

    /** 
    * @Description: 通文件进行序列搜索 
    * @Param:  
    * @return:  
    * @Author: liu qinchang
    * @Date: 2019/9/30 
    */
    @PostMapping("/seqSearchByFile")
    public List<SequenceSearchResult> seqSearchByFile(@RequestParam MultipartFile file){
        if(!file.isEmpty()){
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(
                        new File("/src/main/resources/seqsearch/condition/"+file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }       //接收文件到resources下的文件夹中
            List<SequenceSearchResult> result = blastpSearchProteinService.fileSearchProtein(new File("/src/main/resources/seqsearch/condition/"+file.getOriginalFilename()));
            return result;
        }
        else{
            log.info("接收文件为空");
            return null;
        }
    }

    /** 
    * @Description: 通过序列进行搜索
    * @Param:  
    * @return:  
    * @Author: liu qinchang
    * @Date: 2019/9/30 
    */
    @GetMapping("/seqSearchByStr")
    public List<SequenceSearchResult> seqSearchByStr(@RequestParam String sequence){
        log.info("传入参数:{}",sequence);
        long startTime = System.currentTimeMillis();
        if(StringUtils.isEmpty(sequence)){
            throw new ServiceException("序列为空");
        }
        List<SequenceSearchResult> result = blastpSearchProteinService.seqSearchProtein(sequence);
        log.info("seqSearchByStr调用成功，用时 {} ms",System.currentTimeMillis() - startTime);
        return result;
    }


}
