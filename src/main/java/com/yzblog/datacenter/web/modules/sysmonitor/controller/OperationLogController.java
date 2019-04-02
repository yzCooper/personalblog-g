package com.yzblog.datacenter.web.modules.sysmonitor.controller;

import com.yzblog.datacenter.web.core.controller.PagableQueryController;
import com.yzblog.datacenter.web.core.mybatis.query.Page;
import com.yzblog.datacenter.web.core.vo.Result;
import com.yzblog.datacenter.web.modules.sysmonitor.service.OperationLogService;
import com.yzblog.datacenter.web.modules.sysmonitor.vo.OperationLogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 应用信息控制器
 *
 * @author yuzhou
 * @create 2017-10-25 10:11
 **/
@RestController
@RequestMapping(value="/sysmonitor/operationLog/")
public class OperationLogController extends PagableQueryController {

    private final OperationLogService operationLogService;

    @Autowired
    public OperationLogController(OperationLogService operationLogService) {
        this.operationLogService = operationLogService;
    }

    /**
     * 分页查询应用信息
     * @return page name
     */
    @RequestMapping(value="selectByPage")
    @ResponseBody
    public Result selectByPage(HttpServletRequest request){
        Page<OperationLogVO> page=this.getPage(request);
        Page<OperationLogVO> list=operationLogService.selectOperationLog(page);
        return renderSuccess(list);
    }



}
