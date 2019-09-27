package com.nwafu.PISMDB.controller;

import com.nwafu.PISMDB.entity.CompoundsBasicInformationBean;
import com.nwafu.PISMDB.entity.Targets;
import com.nwafu.PISMDB.service.TargetsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
//@RequestMapping("/data")
public class TargetsController {
    @Autowired
  private TargetsService targetsService;
//    @RequestMapping("/browse-T")
//    @ResponseBody
//    //@Test
//    public List<Targets> showTargets1() {
//        List<Targets> list = targetsService.findTargetById();
//        System.out.println("data数据" + list.size());
//
//        return list;
//    }
}
