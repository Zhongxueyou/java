package com.dao;

import com.pojo.User;

public interface UserDao {
    //登录
    User login(String name, String password);

    //注册
    void register(User user);

    //判断是否重名
    User checkName(String name);

}
