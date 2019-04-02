package com.yzblog.datacenter.web.modules.sys.service;


import com.yzblog.datacenter.web.modules.sys.entity.User;


/**
 * 用户基本操作
 *
 * @author yuzhou
 */
public interface UserService {
    User findSysUserInfo(String name, String pwd);

    User findUserInfo(String name);

    User getUserByID(Long userId);

    User getUserByZgh(String zgh);
}
