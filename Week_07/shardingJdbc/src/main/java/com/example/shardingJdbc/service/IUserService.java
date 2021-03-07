package com.example.shardingJdbc.service;

import com.example.shardingJdbc.entity.User;

public interface IUserService {
    Object save(User user);
    Object list();
}
