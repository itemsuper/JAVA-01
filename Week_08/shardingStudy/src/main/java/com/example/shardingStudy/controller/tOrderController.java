package com.example.shardingStudy.controller;

import com.example.shardingStudy.model.tOrder;
import com.example.shardingStudy.model.tOrderItem;
import com.example.shardingStudy.service.tOrderItemService;
import com.example.shardingStudy.service.tOrderService;
import io.shardingsphere.transaction.annotation.ShardingTransactionType;
import io.shardingsphere.transaction.api.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class tOrderController {
    @Autowired
    tOrderService tOrderService;
    @Autowired
    tOrderItemService tOrderItemService;

    @RequestMapping("/getAll")
    public List<tOrder>getAll(){
        return tOrderService.getAll();
    }

    @RequestMapping("/getOrderByID")
    @ResponseBody
    public tOrder getOrderByID(@RequestParam Long id){
        return tOrderService.getOrderByID(id);
    }

    @RequestMapping("/insert")
    public void insert(@RequestBody tOrder o){
        tOrderService.insert(o);
    }

    @RequestMapping("/update")
    public void update(@RequestBody tOrder o){
        tOrderService.update(o);
    }

    @RequestMapping("/delete")
    public void delete(@RequestParam Long id){
        tOrderService.delete(id);
    }

    /**
     * 测试事务
     */
    @RequestMapping("/tranctionTest")
    @ShardingTransactionType(value = TransactionType.XA)
    @Transactional(rollbackFor = Exception.class)
    public void insertBoth(){
        tOrder o = tOrder.builder().userId(114).STATUS("0025").build();
        tOrderService.insert(o);
        System.out.println("--------access---------");

        int i = 10/0;

        tOrderItem oi = tOrderItem.builder().orderId(657888L).userId(114).STATUS("0026").build();
        tOrderItemService.insert(oi);
    }
}
