package com.nwafu.PISMDB.controller;

import com.nwafu.PISMDB.entity.*;
import com.nwafu.PISMDB.service.CompoundsService;
import com.nwafu.PISMDB.service.PathwaysService;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
//@RequestMapping("/data")
@Slf4j
public class PathwaysController {
    @Autowired
    private PathwaysService pathwaysService;

    @Autowired
    private CompoundsService compoundsService;

    @RequestMapping("/getPathwaysCount")
    @ResponseBody
    public int getTargetsCount() {
        return pathwaysService.getPathwaysCount();
    }

    @RequestMapping("/getPicCount")
    @ResponseBody
    public int getPICCount() {
        return pathwaysService.getPicturesCount();
    }

    @GetMapping("/getPathwaysByPISMID")
    @ResponseBody
    public CompoundsPathway getPathwaysByPISMDB(String pismid){
        return pathwaysService.getPathwaysByPISMID(pismid);
    }

    @ApiOperation(value = "显示所有pathway", notes = "显示所有pathway")
    @RequestMapping(value = "/showAllPathwaysInformation",method = RequestMethod.GET)
    @ResponseBody
    public List<Pictures> showPicsAllinformation() {//@RequestParam String callback
        return pathwaysService.showAllPictureInformation();
    }

    @ApiOperation(value = "显示所有pathway", notes = "显示所有pathway")
    @RequestMapping(value = "/showAllPathways",method = RequestMethod.GET)
    @ResponseBody
    public String showPicsAll(@RequestParam String callback) {
        long startTime = System.currentTimeMillis();
        List<Pic> list1 = pathwaysService.showPictures();
        List<BrowsePathways> pathways = new ArrayList<>();
        for(int i=0;i<pathwaysService.getPicturesCount();i++){
            List<Pictures> listPre = pathwaysService.showPictureInformation(list1.get(i).getId());
//            log.info("listPre:{}",listPre);
            List<Pictures> list = new ArrayList<>();
            for(Pictures p : listPre){
                if(p != null){
                    if(p.getGroupName() != null){
                        p.setGroupName(p.getGroupName().trim());
                    }
                    list.add(p);
                }
            }
//            log.info("pictires:{}",list);
            BrowsePathways p1 = new BrowsePathways();
            List<GroupFormat> groupFormats=new ArrayList<>();
            for(int j=0;j<list.size();j++){
                GroupFormat groupFormat=new GroupFormat();
                groupFormat.setGroupName(list.get(j).getGroupName());
                if(list.get(j).getMolecularPISMID()!= null) {
                    groupFormat.setId(Arrays.asList(list.get(j).getMolecularPISMID().split("%%")));
                }
                if(groupFormat.getGroupName() != null || groupFormat.getId() != null){
                    groupFormats.add(groupFormat);
                }
            }
            log.info("compoundsGroup:{}",groupFormats);
            List<GroupFormat> groupFormat1 = new ArrayList<>();
            for (int k = 0; k < list.size(); k++) {
                GroupFormat groupFormat = new GroupFormat();
                groupFormat.setGroupName(list.get(k).getProteinName());
                if (list.get(k).getProteinTargetID() != null) {
                    groupFormat.setId(Arrays.asList(list.get(k).getProteinTargetID().split("%%")));
                }
                if(groupFormat.getId() != null || groupFormat.getGroupName() != null){
                    groupFormat1.add(groupFormat);
                }
                log.info("proteinInfo:{}",groupFormat1);
            }
            p1.setCompoundGroup(groupFormats);
            p1.setPic(list1.get(i));
            p1.setPictures(list);
            p1.setProteinGroup(groupFormat1);
            pathways.add(p1);
        }
        log.info("调用showPicsAll，用时 {} ms",System.currentTimeMillis() - startTime);
        log.info("返回值为：{}",pathways);
        CallbackResult<List<BrowsePathways>> result = new CallbackResult();
        result.setCallback(callback);
        result.setData(pathways);
        return result.changToJsonp();
    }

    @ApiOperation(value = "跳转到Pathway页面", notes = "跳转到Pathway页面")
    @GetMapping("/Browse_P")
    public String Browse_P() {
        return "Browse-Pathway";
    }

    @ApiOperation(value = "显示pathway", notes = "显示pathway")
    @RequestMapping(value = "/show",method = RequestMethod.GET)
    @ResponseBody
    public String showPics(@RequestParam String callback) {
        List<Pic> list1 = pathwaysService.showPictures();
        List<Pictures> list = pathwaysService.showPictureInformation(list1.get(2).getId());
        List<BrowsePathways> pathways = new ArrayList<>();
        BrowsePathways p1 = new BrowsePathways();
        List<GroupFormat> groupFormats = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            GroupFormat groupFormat = new GroupFormat();
            groupFormat.setGroupName(list.get(i).getGroupName());
            if (list.get(i).getMolecularPISMID() != null) {
                groupFormat.setId(Arrays.asList(list.get(i).getMolecularPISMID().split("%%")));
            }
            groupFormats.add(groupFormat);
        }
        List<GroupFormat> groupFormat1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            GroupFormat groupFormat = new GroupFormat();
            groupFormat.setGroupName(list.get(i).getProteinName());
            if (list.get(i).getProteinTargetID() != null) {
                groupFormat.setId(Arrays.asList(list.get(i).getProteinTargetID().split("%%")));
            }
            groupFormat1.add(groupFormat);
        }
        p1.setPic(list1.get(2));
        p1.setPictures(list);
        p1.setProteinGroup(groupFormat1);
        p1.setCompoundGroup(groupFormats);
        pathways.add(p1);
        int size = list.size();
        System.out.println(size);
        CallbackResult<List<BrowsePathways>> result = new CallbackResult();
        result.setCallback(callback);
        result.setData(pathways);
        log.info("{}", result.changToJsonp());
        return result.changToJsonp();

    }
}
