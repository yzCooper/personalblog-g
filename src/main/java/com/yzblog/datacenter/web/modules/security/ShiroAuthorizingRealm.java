package com.yzblog.datacenter.web.modules.security;

import com.yzblog.datacenter.web.core.exception.EFWebException;
import com.yzblog.datacenter.web.modules.sys.entity.User;
import com.yzblog.datacenter.web.modules.sys.service.UserService;
import com.yzblog.datacenter.web.modules.sys.util.Constans;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 自定义权限认证
 * Created by hua on 2017/8/10.
 */
public class ShiroAuthorizingRealm extends AuthorizingRealm {

    private final UserService userSerice;

    public ShiroAuthorizingRealm(UserService userSerice) {
        this.userSerice = userSerice;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken aToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) aToken;
        Session session = SecurityUtils.getSubject().getSession();
        String userName = token.getUsername();
        String userPwd = new String(token.getPassword());
        User user = new User();
        if(session.getAttribute("type").equals("user")){
            user = userSerice.findUserInfo(userName);
        }else{
            user = userSerice.findSysUserInfo(userName, userPwd);
        }
        if (user == null) {
            throw new EFWebException("账户或密码错误！");
        }
        session.setAttribute(Constans.USER_ID, user.getUserId());
        session.setAttribute(Constans.USER_NAME, user.getUsername());
        session.removeAttribute("type");
        // 认证缓存信息
        return new SimpleAuthenticationInfo(user.getUserId(), userPwd.toCharArray(), getName());
    }

    //shiro的权限配置方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Session session = SecurityUtils.getSubject().getSession();
        Long userId = (Long) session.getAttribute(Constans.USER_ID);
        return getAuthorizationInfo(userId);
    }

    /**
     * 获取认证信息
     *
     * @param userId 用户id
     * @return SimpleAuthorizationInfo
     */
    private AuthorizationInfo getAuthorizationInfo(Long userId) {
        // 授权用户角色
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        return simpleAuthorizationInfo;
    }

}
