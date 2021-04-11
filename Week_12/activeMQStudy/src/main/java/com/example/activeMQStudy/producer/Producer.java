package com.example.activeMQStudy.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;

@RestController
@RequestMapping("test")
public class Producer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Queue queue;
    @Autowired
    private Topic topic;
    /**
     * 发送queue消息
     */
    @RequestMapping(value = "/queue", method = RequestMethod.GET)
    public String sendQueue(String msg) {
        jmsMessagingTemplate.convertAndSend(queue, msg);
        return "queueSuccess";
    }

    /**
     * 发送Topic消息
     */
    @RequestMapping(value = "/topic", method = RequestMethod.GET)
    public String sendTopic(String msg) {
        jmsMessagingTemplate.convertAndSend(topic, msg);
        return "TopicSuccess";
    }
}
