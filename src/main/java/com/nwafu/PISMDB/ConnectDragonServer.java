package com.nwafu.PISMDB;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @program: PISMDB
 * @description: 在项目启动时自动连接Dragon服务器
 * @author: liu qinchang
 * @create: 2019-10-15 11:51
 **/

public class ConnectDragonServer implements ApplicationRunner {
    @Value("${Dragon.url}")
    private String url;
    @Value("${Dragon.username}")
    private String username;
    @Value("${Dragon.psw}")
    private String psw;
    @Value("${Dragon.port}")
    private String port;

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
