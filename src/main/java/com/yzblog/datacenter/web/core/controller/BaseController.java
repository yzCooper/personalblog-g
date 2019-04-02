package com.yzblog.datacenter.web.core.controller;


import com.yzblog.datacenter.web.core.vo.Result;
import com.yzblog.datacenter.web.modules.sys.entity.User;
import com.yzblog.datacenter.web.modules.sys.service.UserService;
import com.yzblog.datacenter.web.modules.sys.util.Constans;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 基础controller
 *
 * @author yuzhou
 */
public abstract class BaseController<T> {

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        // 自动转换日期类型的字段格式
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
    }

    /**
     * 获取当前登录者对象
     */
    protected User getCurrentUser(UserService userService, HttpSession session) {
        Long userId = getCurrentUserId(session);
        return userService.getUserByID(userId);
    }

    /**
     * 获取当前登录者id
     */
    protected Long getCurrentUserId(HttpSession session) {
        return (Long) session.getAttribute(Constans.USER_ID);
    }

    /**
     * ajax失败
     *
     * @param msg 失败的消息
     * @return Result
     */
    protected Result renderError(String msg) {
        Result result = new Result();
        result.setSuccess(false);
        result.setMsg(msg);
        return result;
    }

    /**
     * ajax成功
     *
     * @return Result
     */
    protected Result renderSuccess() {
        Result result = new Result();
        result.setSuccess(true);
        return result;
    }

    /**
     * ajax成功
     *
     * @param msg 消息
     * @return Result
     */
    protected Result renderSuccess(String msg) {
        Result result = new Result();
        result.setSuccess(true);
        result.setMsg(msg);
        return result;
    }

    /**
     * ajax成功
     *
     * @param data 成功时的数据对象
     * @return Result
     */
    public Result renderSuccess(Object data) {
        Result result = new Result();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    /**
     * 获取long类型参数值
     *
     * @param request HttpServletRequest
     * @param name    参数名称
     * @return value
     */
    public String getLong(HttpServletRequest request, String name) {
        String value = request.getParameter(name);
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return value;
    }


    /**
     * 获取int类型参数值
     *
     * @param request HttpServletRequest
     * @param name    参数名称
     * @return value
     */
    public Integer getInt(HttpServletRequest request, String name) {
        String value = request.getParameter(name);
        if (StringUtils.isBlank(value)) {
            return null;
        }
        return Integer.parseInt(value);
    }

}
