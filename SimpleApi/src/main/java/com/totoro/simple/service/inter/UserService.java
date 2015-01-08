package com.totoro.simple.service.inter;

import com.totoro.simple.entity.TUser;

/**
 * Created by Chen on 15-1-5.
 */
public interface UserService {

    public boolean login(String userName, String password);

    public boolean save(TUser user);
}

