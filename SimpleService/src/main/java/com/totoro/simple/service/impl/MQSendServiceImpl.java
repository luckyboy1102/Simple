package com.totoro.simple.service.impl;

import com.totoro.simple.service.inter.MQSendService;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * 消息发送服务
 * Created by Chen on 2015/1/28.
 */
@Service("MQSendServiceImpl")
public class MQSendServiceImpl implements MQSendService {

    @Resource(name = "jmsTemplate")
    private JmsTemplate jmsTemplate;

    public void send(final String message) {
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(message);
                return textMessage;
            }
        });
    }
}
