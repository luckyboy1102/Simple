package com.totoro.simple.service.impl;

import com.totoro.simple.entity.TUser;
import com.totoro.simple.service.inter.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:applicationContext.xml",
        "classpath:applicationContext-simple.xml"
})
public class UserServiceImplTest {

    @Resource(name = "UserServiceImpl")
    private UserService userService;

    @Before
    public void setUp() throws Exception {}

    @Test
    public void testLogin() throws Exception {
        assertTrue(userService.login("a", "a"));
    }

    @Test
    public void testSave() throws Exception {
        TUser user = new TUser();
        user.setId("123");
        user.setName("Chen");
        user.setPassword("chen");
        user.setLoginName("totoro");
        assertTrue(userService.save(user));
    }
}