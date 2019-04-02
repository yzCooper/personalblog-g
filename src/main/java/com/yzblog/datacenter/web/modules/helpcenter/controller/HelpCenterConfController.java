package com.yzblog.datacenter.web.modules.helpcenter.controller;

import com.yzblog.datacenter.web.core.controller.BaseController;
import com.yzblog.datacenter.web.core.vo.Result;
import com.yzblog.datacenter.web.modules.helpcenter.entity.ApplicationList;
import com.yzblog.datacenter.web.modules.helpcenter.entity.HelpCenterSource;
import com.yzblog.datacenter.web.modules.helpcenter.entity.ModuleList;
import com.yzblog.datacenter.web.modules.helpcenter.pojo.AppModulePo;
import com.yzblog.datacenter.web.modules.helpcenter.service.AppModuleSerivce;
import com.yzblog.datacenter.web.modules.helpcenter.service.BlogSourceService;
import com.yzblog.datacenter.web.modules.helpcenter.service.HelpCenterInfoService;
import com.yzblog.datacenter.web.modules.helpcenter.vo.HelpSourceVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author yuzhou
 * @Description 博客管理端业务
 * @date created in 2018/12/21 14:26
 */
@RestController
@RequestMapping(value = "/manage")
public class HelpCenterConfController extends BaseController {

    private static  final Logger log = LoggerFactory.getLogger(HelpCenterConfController.class);

    @Autowired
    AppModuleSerivce appModuleSerivce;

    @Autowired
    BlogSourceService blogSourceService;

    @Autowired
    HelpCenterInfoService helpCenterInfoService;

    @Value("${help.center.source.path}")
    private String path;

    @PostMapping(value = "/uploadWordToHtml")
    public Result uploadWordToHtml(@RequestBody HelpSourceVo helpSourceVo) throws Exception {
        int result = 0;
        if(StringUtils.isBlank(helpSourceVo.getSid())){
            String sid = UUID.randomUUID().toString().replace("-","");
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append(helpSourceVo.getAppName())
                    .append(File.separator)
                    .append(sid)
                    .append(helpSourceVo.getModuleName());
            String newpath = new StringBuilder(path).append(strBuilder).toString();
            HelpCenterSource helpCenterSource = new HelpCenterSource();
            helpCenterSource.setWid(helpSourceVo.getWid());
            helpCenterSource.setMid(helpSourceVo.getMid());
            helpCenterSource.setSid(sid);
            helpCenterSource.setPath(strBuilder.toString());
            result = appModuleSerivce.saveSource(newpath,helpSourceVo.getSourcedata(),helpCenterSource);
        }else{
            HelpCenterSource helpCenterSource = blogSourceService.selectByPrimaryKey(helpSourceVo.getSid());
            result = appModuleSerivce.saveSource(new StringBuilder(path).append(helpCenterSource.getPath()).toString(),helpSourceVo.getSourcedata(),helpCenterSource);
        }
        if(result > 0) {
            return renderSuccess("博客添加成功");
        }else{
            return renderError("博客添加失败");
        }
    }
    @PostMapping(value = "/helpinfo")
    public Result helpinfo(@RequestBody HelpSourceVo helpSourceVo){
        String source = helpCenterInfoService.helpCenterInfo(helpSourceVo);
        return renderSuccess((Object)source);
    }

    @PostMapping(value = "/savehelpsource")
    public Result savehelpsource(@RequestBody HelpSourceVo helpSourceVo){
        StringBuilder strBuilder = new StringBuilder();
        HelpCenterSource helpCenterSource = new HelpCenterSource();
        strBuilder.append(helpSourceVo.getWid());
        strBuilder.append("\\");
        strBuilder.append(helpSourceVo.getMid());
        strBuilder.append("\\");
        strBuilder.append(helpSourceVo.getWid());
        strBuilder.append(helpSourceVo.getMid());
        helpCenterSource.setWid(helpSourceVo.getWid());
        helpCenterSource.setMid(helpSourceVo.getMid());
        String sid = UUID.randomUUID().toString().replace("-","");
        helpCenterSource.setSid(sid);
        helpCenterSource.setPath(strBuilder.toString());
        StringBuilder newpath = new StringBuilder();
        newpath.append(path);
        newpath.append(strBuilder);
        int result = appModuleSerivce.saveSource(newpath.toString(),helpSourceVo.getSourcedata(),helpCenterSource);
        if(result > 0) {
            return renderSuccess("博客添加成功");
        }else{
            return renderError("博客添加失败");
        }
    }
    @GetMapping(value = "/getapplicationname")
    public Result getapplicationname(){
        Map<String,List<AppModulePo>> map = appModuleSerivce.selectApplicationAndModule();
        return renderSuccess(map);
    }

    @PostMapping(value = "/getappModuleFromWid")
    public Result getappModuleFromWid(@RequestBody HelpSourceVo helpSourceVo){
        if(helpSourceVo.getWid() != null){
            List<AppModulePo> list = appModuleSerivce.selectFromWid(helpSourceVo.getWid());
            return renderSuccess(list);
        }else{
            Map<String,List<AppModulePo>> map = appModuleSerivce.selectApplicationAndModule();
            return renderSuccess(map);
        }
    }

    @PostMapping(value = "/addApplication")
    public Result addApplication(@RequestBody ApplicationList applicationList){
        Date date  = new Date();
        applicationList.setCreateTime(date);
        String wid = UUID.randomUUID().toString().replace("-","");
        applicationList.setWid(wid);
        int res = appModuleSerivce.insertSelective(applicationList);
        if(res > 0){
            return renderSuccess("应用添加成功");
        }else{
            return renderError("应用添加失败");
        }
    }

    @PostMapping(value = "/updateApplication")
    public Result updateApplication(@RequestBody ApplicationList applicationList){
        Date date  = new Date();
        applicationList.setUpdateTime(date);
        int res = appModuleSerivce.updateApplication(applicationList);
        if(res > 0){
            return renderSuccess("应用名称修改成功");
        }else{
            return renderError("应用名称修改失败");
        }
    }

    @PostMapping(value = "/delApplication")
    public Result delApplication(@RequestBody ApplicationList applicationList){
        Date date  = new Date();
        applicationList.setUpdateTime(date);
        int res = appModuleSerivce.delApplication(applicationList.getWid());
        if(res > 0){
            return renderSuccess("应用删除成功");
        }else{
            return renderError("应用删除失败");
        }
    }

    @PostMapping(value = "/addModule")
    public Result addModule(@RequestBody ModuleList moduleList){
        Date date  = new Date();
        moduleList.setCreateTime(date);
        String mid = UUID.randomUUID().toString().replaceAll("-", "");
        moduleList.setMid(mid);
        int res = appModuleSerivce.insertSelective(moduleList);
        if(res > 0){
            return renderSuccess("模块添加成功");
        }else{
            return renderError("模块添加失败");
        }
    }

    @PostMapping(value = "/updateModule")
    public Result updateModule(@RequestBody ModuleList moduleList){
        Date date  = new Date();
        moduleList.setUpdateTime(date);
        int res = appModuleSerivce.updateModule(moduleList);
        if(res > 0){
            return renderSuccess("模块名称修改成功");
        }else{
            return renderError("模块名称修改失败");
        }
    }

    @PostMapping(value = "/delModule")
    public Result delModule(@RequestBody ModuleList moduleList){
        Date date  = new Date();
        moduleList.setUpdateTime(date);
        int res = appModuleSerivce.delModule(moduleList.getMid());
        if(res > 0){
            return renderSuccess("模块删除成功");
        }else{
            return renderError("模块删除失败");
        }
    }
}
