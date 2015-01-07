package com.totoro.simple.service.impl;

import com.totoro.simple.dao.inter.UserDAO;
import com.totoro.simple.service.inter.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by admin on 2015/1/7.
 */
public class UserServiceImpl implements UserService {

    @Resource(name = "UserDAOImpl")
    private UserDAO userDAO;

    @Override
    public boolean login(String userName, String password) {
        return userDAO.login(userName, password);
    }
}
