package com.totoro.simple.dao.inter;

import com.totoro.framework.dao.inter.FrameworkDAO;
import com.totoro.simple.entity.TUser;

import java.util.Map;

/**
 * 用户DAO接口
 * Created by Chen on 15-1-5.
 */
public interface UserDAO extends FrameworkDAO<TUser> {

    public TUser getUserByConditoin(Map<String, Object> condition);
}
