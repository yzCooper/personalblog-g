package com.yzblog.datacenter.web.modules.sys.dao;


import com.yzblog.datacenter.web.modules.sys.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    User selectByPrimaryKey(Long id);

    User findSysUserInfo(@Param("name") String name,@Param("pwd") String pwd);

    User findUserInfo(@Param("name") String name);

    User getUserByZgh(@Param("zgh") String zgh);
}