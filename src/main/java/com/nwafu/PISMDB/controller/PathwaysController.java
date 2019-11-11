package com.nwafu.PISMDB.controller;

import com.nwafu.PISMDB.entity.*;
import com.nwafu.PISMDB.service.CompoundsService;
import com.nwafu.PISMDB.service.PathwaysService;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    @RequestMapping(value = "/showAllPathways",method = RequestMethod.GET)
    @ResponseBody
    public String showPicsAll(@RequestParam String callback) {
        List<Pic> list1 = pathwaysService.showPictures();
        List<BrowsePathways> pathways = new ArrayList<>();
        for(int i=0;i<pathwaysService.getPicturesCount();i++){
            List<Pictures> list = pathwaysService.showPictureInformation(list1.get(i).getId());
            BrowsePathways p1 = new BrowsePathways();
            p1.setPic(list1.get(i));
            p1.setPictures(list);
            pathways.add(p1);
        }
        CallbackResult<List<BrowsePathways>> result = new CallbackResult();
        result.setCallback(callback);
        result.setData(pathways);
        log.info("{}",result.changToJsonp());
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
        List<Pictures> list = pathwaysService.showPictureInformation(list1.get(0).getId());
        List<BrowsePathways> pathways=new ArrayList<>();
        BrowsePathways p1=new BrowsePathways();

        p1.setPic(list1.get(0));
        p1.setPictures(list);
        pathways.add(p1);
        int size = list.size();
        System.out.println(size);
        CallbackResult<List<BrowsePathways>> result = new CallbackResult();
        result.setCallback(callback);
        result.setData(pathways);
        log.info("{}",result.changToJsonp());
        return result.changToJsonp();


     /*   Pictures pic[]=new Pictures[size];

        for(int i=0;i<size;i++)
        {
            float startX=list.get(i).getStartX();
            float startY=list.get(i).getStartY();
            float endX=list.get(i).getEndX();
            float endY=list.get(i).getEndY();
            //String url=list.get(i).getUrl();
            String information=list.get(i).getInformation();

            pic[i]=new Pictures(startX,startY,endX,endY,information);
            System.out.println(pic[i].toString());

            //request.setAttribute("p1",pic[i]);
        }
//        float startX=list.get(0).getStartX();
//        float startY=list.get(0).getStartY();
//        float endX=list.get(0).getEndX();
//        float endY=list.get(0).getEndY();
//        String information=list.get(0).getInformation();
//
//        String url=list.get(0).getUrl();
//
//        Pictures p1=new Pictures();
//        p1.setStartX(startX);
//        p1.setStartY(startY);
//        p1.setEndX(endX);
//        p1.setEndY(endY);
//        p1.setUrl(url);
//
//        Pictures p2=new Pictures(url,startX,startY,endX,endY,information);
//
//        request.setAttribute("url",url);
//        request.setAttribute("startX",startX);
//        request.setAttribute("startY",startY);
//        request.setAttribute("endX",endX);
//        request.setAttribute("endY",endY);
//
//        request.setAttribute("p1",p1);
        return list;*/
    }

}
