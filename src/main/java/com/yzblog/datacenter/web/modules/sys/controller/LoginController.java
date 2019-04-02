package com.yzblog.datacenter.web.modules.sys.controller;

import com.yzblog.datacenter.web.core.controller.BaseController;

import com.yzblog.datacenter.web.core.util.JsonUtils;
import com.yzblog.datacenter.web.core.util.Md5Util;
import com.yzblog.datacenter.web.core.vo.Result;

import com.yzblog.datacenter.web.modules.sys.service.UserService;
import com.yzblog.datacenter.web.modules.sys.util.Constans;
import com.yzblog.datacenter.web.modules.sys.util.HttpClientResult;
import com.yzblog.datacenter.web.modules.sys.util.HttpClientUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户登录控制器
 *
 * @author yuzhou
 */
@RestController
public class LoginController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 登录逻辑处理
     *
     * @param request reqeust
     * @param model   Spring modelUserFilter
     * @return page name
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result signin(HttpServletRequest request, Model model) {
        String userName = request.getParameter(Constans.USER_NAME);
        String userPwd = request.getParameter(Constans.USER_PWD);
        String logErrorMsg = null;
        if (StringUtils.isBlank(userName)) {
            logErrorMsg = "用户名不能为空。";
        }

        if (logErrorMsg != null) {
            model.addAttribute(Constans.LOGIN_PAGE_MSG, logErrorMsg);
            return renderError("用户名不能为空");
        }
        // 先注销原有认证
        SecurityUtils.getSecurityManager().logout(SecurityUtils.getSubject());
        // 登录后存放进shiro token
        UsernamePasswordToken token = new UsernamePasswordToken(userName, Md5Util.md5(userPwd));
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("type", "sysuser");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            logger.error("---"+e.getCause().getMessage());
            model.addAttribute(Constans.LOGIN_PAGE_MSG, e.getCause().getMessage());
            return renderError(e.getCause().getMessage());
        }
        Map<String,Object> map = new HashMap<>();
        map.put("userName",userName);
        map.put("msg","登陆成功");
        return renderSuccess(map);
    }

    /**
     * 注销页面
     *
     * @return login.jsp
     */
    @RequestMapping(value = "/logout")
    public String logout(Model model) {
        SecurityUtils.getSubject().logout();
        model.addAttribute(Constans.LOGIN_PAGE_MSG, "您已安全退出。");
        return "您已安全退出";
    }
}
