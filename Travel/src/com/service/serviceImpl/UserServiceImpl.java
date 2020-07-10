package com.service.serviceImpl;

import com.dao.UserDao;
import com.dao.daoImpl.UserDaoImpl;
import com.pojo.User;
import com.service.UserService;

public class UserServiceImpl implements UserService {
     UserDao userDao=new UserDaoImpl();
    @Override
    public User login(String name, String password) {
        return userDao.login(name,password);
    }

    @Override
    public void register(User user) {
        userDao.register(user);
    }

    @Override
    public User checkName(String name) {
        return userDao.checkName(name);
    }
}
