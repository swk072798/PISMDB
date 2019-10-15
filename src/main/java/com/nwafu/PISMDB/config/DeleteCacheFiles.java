package com.nwafu.PISMDB.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @program: PISMDB
 * @description: 定时任务：定期删除项目中的缓存文件
 * @author: liu qinchang
 * @create: 2019-10-14 15:27
 **/

@Component
@Configuration
@EnableScheduling
@Slf4j
public class DeleteCacheFiles {

    @Scheduled(fixedDelay = 300000)     //定时任务每5分钟删除一次缓存
    public void deleteSeqSearchCache(){
        String dir1Path = "src/main/resources/seqsearch/condition";
        String dir2Path = "src/main/resources/seqsearch/exchangedfasta";
        String dir3Path = "src/main/resources/seqsearch/result";
        File[] dir1Files = new File(dir1Path).listFiles();
        File[] dir2Files = new File(dir2Path).listFiles();
        File[] dir3Files = new File(dir3Path).listFiles();

        Thread thread1 = new Thread(new DeleteThread(dir1Files));
        Thread thread2 = new Thread(new DeleteThread(dir2Files));
        Thread thread3 = new Thread(new DeleteThread(dir3Files));

        thread1.start();
        thread2.start();
        thread3.start();    //开三个线程同步删，防止文件过多，过于占用系统资源

    }
}

@Slf4j
class DeleteThread implements Runnable{
    private File[] files = null;

    public DeleteThread(File[] files){
        this.files = files;
    }
    public void setFiles(File[] files){
        this.files = files;
    }
    @Override
    public void run() {
        if(files == null || files.length == 0){
            log.info("文件夹中没有缓存");
            return;
        }
        for(File f : files){
            if(f.exists()){
                if(f.delete()){
                    log.info("删除缓存文件成功,{}",f.getName());
                }
                else{
                    log.info("删除文件失败，可能是文件正在被占用");
                }
            }
            else{
                log.info("{}, 文件不存在",f.getName());
            }
        }
    }
}