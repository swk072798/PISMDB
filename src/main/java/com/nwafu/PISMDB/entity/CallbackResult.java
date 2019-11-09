package com.nwafu.PISMDB.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @program: PISMDB
 * @description: 用于最终callback结果集的封装
 * @author: liu qinchang
 * @create: 2019-11-09 15:21
 **/


@Data
public class CallbackResult<T> {
    private String callback;
    private T data;

    public String changToJsonp(){
        return callback + "(" + JSONObject.toJSONString(data) + ")";
    }

}
