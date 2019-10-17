package com.nwafu.PISMDB.service.impl;

import com.jcraft.jsch.*;
import com.nwafu.PISMDB.service.ConnectServerAndChangeFileService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.python.icu.util.Output;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Properties;

/**
 * @program: PISMDB
 * @description:
 * @author: liu qinchang
 * @create: 2019-10-15 13:35
 **/
@Service
@Slf4j
@Data
public class ConnectServerAndChangeFileServiceImpl implements ConnectServerAndChangeFileService {
    private Session sshSession = null;
    private ChannelSftp sftp = null;
    public static int isConnect = 0;
    private String url;
    private String username;
    private String psw;
    private int port;

    private static ConnectServerAndChangeFileServiceImpl connectServerAndChangeFileService = new ConnectServerAndChangeFileServiceImpl();

    private ConnectServerAndChangeFileServiceImpl(){}       //这里
    /** 
    * @Description: 连接服务器
    * @Param:  
    * @return:  
    * @Author: liu qinchang
    * @Date: 2019/10/15 
    */

    public static ConnectServerAndChangeFileServiceImpl getInstance(){
        return connectServerAndChangeFileService;
    }

    @Override
    public void connectToSever() {
        log.info("--->连接Dragon服务器开始------");
        JSch jSch = new JSch();
        try {
            jSch.getSession(username, url, port);
            sshSession = jSch.getSession(username, url, port);
            sshSession.setPassword(psw);
            Properties properties = new Properties();
            properties.put("StrictHostKeyChecking","no");
            sshSession.setConfig(properties);
            sshSession.connect();
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp)channel;
            log.info("ftp Connect to {}:{}",url,port);
            isConnect = 1;
        } catch (JSchException e) {
            log.error("连接失败");
            throw new RuntimeException("sftp连接超时");
        }
    }

    @Override
    public void disConnect() {
        if(this.sftp != null){
            if(this.sftp.isConnected()){
                this.sftp.disconnect();
                this.sftp = null;
                log.info("sftp 连接已关闭");
            }
        }
        if(this.sshSession != null){
            if(this.sshSession.isConnected()){
                this.sshSession.disconnect();
                this.sshSession = null;
                log.info("sshSession 连接已关闭");
            }
            isConnect = 0;
        }
    }

    /** 
    * @Description: 将文件从本地服务器上传到Dragon服务器
    * @Param:  
    * @return:  
    * @Author: liu qinchang
    * @Date: 2019/10/15 
    */
    @Override
    public boolean uploadFile(String remoteFilePath, String localFilePath) {
        log.info("upload file from {} to {}",localFilePath,remoteFilePath);
        if(isConnect == 0){
            connectToSever();
            log.info("上传文件时服务器未连接，正在重新连接。。。。");
        }
        File localFile = new File(localFilePath);
        FileInputStream in = null;
        try {
            in = new FileInputStream(localFile);
            sftp.put(in,remoteFilePath);
        } catch (FileNotFoundException e) {
            log.error("待计算描述符的本地文件不存在");
            e.printStackTrace();
        } catch (SftpException e) {
            log.error("sftp 异常");
            e.printStackTrace();
        }
        finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    log.error("in 关闭异常？？？");
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    @Override
    public boolean caculateMolecularDescriptor() {
        return false;
    }

    @Override
    public boolean dowloadResultFile(String remoteFilePath, String localFilePath) {
        File localFile = new File(localFilePath);
        OutputStream out = null;
        try {
            localFile.createNewFile();
            out = new FileOutputStream(localFile);
            sftp.get(remoteFilePath,out);
            log.info("下载文件---{}",remoteFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        }
        finally {
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        log.info("下载文件结束");
        try {
            sftp.rm(remoteFilePath);
            log.info("删除Dragon服务器端缓存文件");
        } catch (SftpException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void deleteCacheFiles() {

    }
}
