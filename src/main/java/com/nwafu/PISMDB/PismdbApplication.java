package com.nwafu.PISMDB;

import com.alibaba.fastjson.serializer.SerializerFeature;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@SpringBootApplication
@MapperScan("com.nwafu.PISMDB.dao")//让其扫描dao层接口。
@EnableSwagger2
public class PismdbApplication{
    public static void main(String[] args) {
        log.info("项目启动准备中——————");
        SpringApplication.run(PismdbApplication.class, args);
        log.info("项目启动成功————————");

    }
}

