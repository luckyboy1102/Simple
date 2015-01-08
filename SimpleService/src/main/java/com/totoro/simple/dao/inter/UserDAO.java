package com.totoro.simple.dao.inter;

import com.totoro.simple.dao.FrameworkDAO;
import com.totoro.simple.entity.TUser;

/**
 * Created by Chen on 15-1-5.
 */
public interface UserDAO extends FrameworkDAO<TUser> {

    public boolean login(String userName, String password);
}
