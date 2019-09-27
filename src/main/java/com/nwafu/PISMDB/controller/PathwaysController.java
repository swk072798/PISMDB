package com.nwafu.PISMDB.controller;

import com.nwafu.PISMDB.service.PathwaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/data")
public class PathwaysController {
    @Autowired
    private PathwaysService pathwaysService;

    @RequestMapping("/getPathwaysCount")
    @ResponseBody
    public int getTargetsCount() {
        return pathwaysService.getPathwaysCount();
    }
}