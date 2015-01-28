package com.totoro.simple.service.inter;

/**
 * 消息发送服务接口
 * Created by Chen on 2015/1/28.
 */
public interface MQSendService {

    public void send(String message);
}
