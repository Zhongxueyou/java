package com.study.blog.service;

import com.study.blog.pojo.User;

public interface UserService {

    User findUser(String username, String password);
    User findUserBy(Long id);
}
