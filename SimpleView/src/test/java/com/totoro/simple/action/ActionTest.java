package com.totoro.simple.action;

import com.totoro.simple.entity.TUser;
import com.totoro.simple.service.inter.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertTrue;

/**
 * Created by Chen on 15-1-13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:applicationContext.xml",
        "classpath:service-consumer-simple.xml"
})
public class ActionTest {

    @Resource(name = "UserService")
    private UserService userService;

    @Test
    public void testLogin() throws Exception {
        assertTrue(userService.login("a", "a"));
    }

    @Test
    public void testSave() {
        TUser user = new TUser();
        user.setName("chenshuai1");
        user.setPassword("chenshuai1");
        user.setLoginName("totoro");
        assertTrue(userService.save(user));
    }
}
