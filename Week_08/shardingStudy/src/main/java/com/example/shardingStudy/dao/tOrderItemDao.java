package com.example.shardingStudy.dao;

import com.example.shardingStudy.model.tOrderItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface tOrderItemDao {

    /**
     * 增加
     * @param o
     */
    void insert(tOrderItem o);



}
