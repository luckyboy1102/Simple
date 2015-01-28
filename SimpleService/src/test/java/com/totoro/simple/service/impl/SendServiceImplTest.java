package com.totoro.simple.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 消息发送服务测试类
 * Created by Chen on 2015/1/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:applicationContext.xml",
        "classpath:applicationContext-simple.xml"
})
public class SendServiceImplTest {

    @Resource(name = "MQSendServiceImpl")
    private MQSendServiceImpl sendService;

    @Test
    public void testSend() {
        sendService.send("Aha");
    }
}
