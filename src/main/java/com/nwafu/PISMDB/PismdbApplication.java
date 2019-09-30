package com.nwafu.PISMDB;

import com.alibaba.fastjson.serializer.SerializerFeature;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.nwafu.PISMDB.dao")//让其扫描dao层接口。
public class PismdbApplication{
    public static void main(String[] args) {
        SpringApplication.run(PismdbApplication.class, args);

    }
}

