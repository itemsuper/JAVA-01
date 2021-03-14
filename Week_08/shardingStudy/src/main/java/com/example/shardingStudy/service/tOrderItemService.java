package com.example.shardingStudy.service;

import com.example.shardingStudy.dao.tOrderItemDao;
import com.example.shardingStudy.model.tOrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class tOrderItemService {
    @Autowired
    tOrderItemDao tOrderItemDao;

    /**
     * 增加
     * @param o
     */
    public void insert(tOrderItem o){
        tOrderItemDao.insert(o);
    };

}
