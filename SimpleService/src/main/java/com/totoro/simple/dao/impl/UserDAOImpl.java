package com.totoro.simple.dao.impl;

import com.totoro.simple.dao.BaseDAO;
import com.totoro.simple.dao.inter.UserDAO;
import com.totoro.simple.entity.TUser;
import org.springframework.stereotype.Component;

/**
 * Created by Chen on 15-1-5.
 */
@Component("UserDAOImpl")
public class UserDAOImpl extends BaseDAO<TUser> implements UserDAO {

    @Override
    public boolean login(String userName, String password) {
        return true;
    }
}
