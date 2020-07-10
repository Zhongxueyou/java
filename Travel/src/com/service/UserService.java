package com.service;

import com.pojo.User;

public interface UserService {
    User login(String name, String password);
    void register(User user);
    User checkName(String name);
}
