package com.example.shardingStudy.dao;

import com.example.shardingStudy.model.tOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface tOrderDao {
    /**
     * 查询所有
     * @return
     */

    List<tOrder> getAll();

    /**
    根据ID查询
    {id} 要查询订单的 id
     */
    tOrder getOrderByID(Long id);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);

    /**
     * 更新
     * @param o
     */
    void update(tOrder o);

    /**
     * 增加
     * @param o
     */
    void insert(tOrder o);



}
