package com.example.shardingJdbc.dao;

import com.example.shardingJdbc.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    /**
     * 保存
     */
    void save(User user);

    /**
     * 查询
     */
    List<User> list();
}
