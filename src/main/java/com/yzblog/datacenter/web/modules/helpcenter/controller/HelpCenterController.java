package com.yzblog.datacenter.web.modules.helpcenter.controller;


import com.yzblog.datacenter.web.core.controller.BaseController;
import com.yzblog.datacenter.web.core.vo.Result;
import com.yzblog.datacenter.web.modules.helpcenter.entity.ModuleList;
import com.yzblog.datacenter.web.modules.helpcenter.service.HelpCenterInfoService;
import com.yzblog.datacenter.web.modules.helpcenter.vo.HelpSourceVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author yuzhou
 * @Description 博客帮助页面
 * @date created in 2018/12/24 14:00
 */
@RestController
@RequestMapping(value = "/info")
public class HelpCenterController extends BaseController {
    private static  final Logger log = LoggerFactory.getLogger(HelpCenterController.class);

    @Autowired
    HelpCenterInfoService helpCenterInfoService;

    @PostMapping(value = "/helpinfo")
    public Result helpinfo(@RequestBody HelpSourceVo helpSourceVo){
        try{
            String source = helpCenterInfoService.helpCenterInfo(helpSourceVo);
            return renderSuccess((Object)source);
        }catch (Exception e){
            log.info("/info/helpinfo查询出错：" + e.getMessage());
            return renderError("/info/helpinfo查询出错：" +e.getMessage());
        }
    }

    @GetMapping(value ="/categorysource")
    public Result categorysource(){
        try{
            List<Map<String,String>> list = helpCenterInfoService.selectWidAppNameAndNum();
            return renderSuccess(list);
        }catch(Exception e){
            log.info("/info/categorysource：请求出错"+ e.getMessage());
            return renderError("/info/categorysource：请求出错"+ e.getMessage());
        }
    }

    @GetMapping(value = "/timeLineContent")
    public Result timeLineContent(String wid){
        try{
            List<Map<String,String>> list = helpCenterInfoService.selectAllWidMidAndName(wid);
            return renderSuccess(list);
        }catch(Exception e){
            log.info("/info/timeLineContent：请求出错"+ e.getMessage());
            return renderError("/info/timeLineContent：请求出错"+ e.getMessage());
        }
    }

    @GetMapping(value = "/latestContentList")
    public Result latestContentList(){
        try{
            List<ModuleList> list = helpCenterInfoService.latestContentList();
            return renderSuccess(list);
        }catch(Exception e){
            log.info("/info/latestContentList：请求出错"+ e.getMessage());
            return renderError("/info/latestContentList：请求出错"+ e.getMessage());
        }
    }
}
