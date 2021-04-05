package com.example.redisStudy.homework5;

import org.springframework.stereotype.Service;

/**
 * 消息订阅业务处理类
 */
@Service
public class BusinessSubService {
    public void receiveMessage(String message) {
        try {
            // 具体的业务处理
            System.out.println("准备对订单进行处理...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
