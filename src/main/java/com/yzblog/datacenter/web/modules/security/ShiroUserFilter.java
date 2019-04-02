package com.yzblog.datacenter.web.modules.security;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;

/**
 * 重写shiro的UserFilter，实现通过OPTIONS请求
 * @author yuzhou
 */

@Component("shiroUserFilter")
public class ShiroUserFilter extends FormAuthenticationFilter {
        /**
         * url白名单，逗号分隔
         */
        @Value("${security.shiro.url.whiteList}")
        private String urlWhiteList;

        /**
         * 运行访问的url
         */
        private HashSet<String> allowableUrls = new HashSet<>();


        @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
            if (StringUtils.isNoneBlank(urlWhiteList)) {
                String[] urls = urlWhiteList.split(",");
                allowableUrls = new HashSet<>(Arrays.asList(urls));
            }
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;

            String contextPath = httpServletRequest.getContextPath();
            String requestURI = httpServletRequest.getRequestURI();
            // 去掉contextPath
            if (contextPath.length() > 0) {
                requestURI = requestURI.substring(contextPath.length());
            }

            // 白名单处理
            if (allowableUrls.contains(requestURI) || requestURI.indexOf("info") >0) {
                return true;
            }

            if (request instanceof HttpServletRequest) {
            if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
                return true;
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
            throws Exception {
        WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }

}


