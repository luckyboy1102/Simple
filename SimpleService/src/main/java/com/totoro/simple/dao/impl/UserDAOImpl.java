package com.totoro.simple.dao.impl;

import com.totoro.simple.dao.inter.UserDAO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by Chen on 15-1-5.
 */
@Component("UserDAOImpl")
public class UserDAOImpl implements UserDAO {

    @Override
    public boolean login(String userName, String password) {
        return true;
    }
}
