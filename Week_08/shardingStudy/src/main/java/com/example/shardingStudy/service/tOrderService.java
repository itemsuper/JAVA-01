package com.example.shardingStudy.service;

import com.example.shardingStudy.dao.tOrderDao;
import com.example.shardingStudy.model.tOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class tOrderService {
    @Autowired
    tOrderDao tOrderDao;

    /**
     * 查询所有
     * @return
     */

    public List<tOrder> getAll(){
        return tOrderDao.getAll();
    };

    /**
     根据ID查询
     {id} 要查询订单的 id
     */
    public tOrder getOrderByID(Long id){
        return tOrderDao.getOrderByID(id);
    };

    /**
     * 删除
     * @param id
     */
    public void delete(Long id){
        tOrderDao.delete(id);
    };

    /**
     * 更新
     * @param o
     */
    public void update(tOrder o){
        tOrderDao.update(o);
    };

    /**
     * 增加
     * @param o
     */
    public void insert(tOrder o){
        tOrderDao.insert(o);
    };

}
