package com.yzblog.datacenter.web.modules.sys.service.impl;

import com.yzblog.datacenter.web.modules.sys.dao.UserDao;
import com.yzblog.datacenter.web.modules.sys.entity.User;
import com.yzblog.datacenter.web.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * 用户业务逻辑
 *
 * @author yuzhou
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;


    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findSysUserInfo(String name, String pwd) {
        return userDao.findSysUserInfo(name,pwd);
    }

    @Override
    public User findUserInfo(String name) {
        return userDao.findUserInfo(name);
    }

    @Override
    public User getUserByID(Long userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    @Override
    public User getUserByZgh(String zgh) {
        return userDao.getUserByZgh(zgh);
    }
}
