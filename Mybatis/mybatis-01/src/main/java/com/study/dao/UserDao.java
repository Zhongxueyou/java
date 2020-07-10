package com.study.dao;

import com.study.pojo.User;

import java.util.List;

public interface UserDao {

    List<User> getUserList();
    User getUser(int id);
    List<User> getUserTeacherList();
    int insert(User user);

}
