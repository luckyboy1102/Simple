package com.totoro.action.simple.login;

import com.opensymphony.xwork2.ActionSupport;
import com.totoro.simple.service.inter.UserService;

import javax.annotation.Resource;

/**
 * Created by Chen on 14-12-30.
 */
public class UserAction extends ActionSupport {

    @Resource(name = "UserServiceImpl")
    private UserService userService;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String execute() {
        return "SUCCESS";
    }

}
