package com.totoro.simple.service.impl;

import com.totoro.simple.dao.inter.UserDAO;
import com.totoro.simple.service.inter.UserService;

/**
 * Created by admin on 2015/1/7.
 */
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Override
    public boolean login(String userName, String password) {
        return userDAO.login(userName, password);
    }
}
