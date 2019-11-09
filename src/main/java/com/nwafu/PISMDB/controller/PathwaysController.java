package com.nwafu.PISMDB.controller;

import com.nwafu.PISMDB.entity.CompoundsPathway;
import com.nwafu.PISMDB.service.PathwaysService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/getPathwaysByPISMID")
    @ResponseBody
    public CompoundsPathway getPathwaysByPISMDB(String pismid){
        return pathwaysService.getPathwaysByPISMID(pismid);
    }

    @ApiOperation(value = "跳转到Pathway页面", notes = "跳转到Pathway页面")
    @GetMapping("/Browse_P")
    public String Browse_P() {
        return "Browse-Pathway";
    }
}
