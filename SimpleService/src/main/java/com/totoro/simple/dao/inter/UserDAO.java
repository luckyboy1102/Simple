package com.totoro.simple.dao.inter;

import com.totoro.simple.dao.FrameworkDAO;
import com.totoro.simple.entity.TUser;

import java.util.Map;

/**
 * Created by Chen on 15-1-5.
 */
public interface UserDAO extends FrameworkDAO<TUser> {

    public TUser getUserByConditoin(Map<String, Object> condition);
}
