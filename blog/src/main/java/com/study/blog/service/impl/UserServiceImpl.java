package com.study.blog.service.impl;

import com.study.blog.mapper.UserMapper;
import com.study.blog.pojo.User;
import com.study.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findUser(String username, String password) {
        return userMapper.findUser(username,password);
    }

    @Override
    public User findUserBy(Long id) {
        return userMapper.findUserBy(id);
    }
}
