package com.example.activeMQStudy.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @JmsListener(destination = "queue")
    public void listenQuene(String msg) {
        System.out.println("接收到queue的消息：" + msg);
    }

    @JmsListener(destination = "topic")
    public void listenTopic(String msg) {
        System.out.println("接收Topic的消息：" + msg);
    }
}
