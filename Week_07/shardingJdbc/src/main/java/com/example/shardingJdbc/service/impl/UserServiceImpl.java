package com.example.shardingJdbc.service.impl;

import com.example.shardingJdbc.dao.UserDao;
import com.example.shardingJdbc.entity.User;
import com.example.shardingJdbc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements IUserService {
    @Autowired
    UserDao userDao;

    @Override
    public String save(User user) {
        user.setId("1");
        user.setName("it");
        userDao.save(user);
        return "success";
    }

    @Override
    public Object list() {
        return userDao.list();
    }
}
