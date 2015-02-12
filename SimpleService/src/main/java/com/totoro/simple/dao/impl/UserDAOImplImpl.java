package com.totoro.simple.dao.impl;

import com.totoro.framework.dao.impl.FrameworkDAOImpl;
import com.totoro.simple.dao.inter.UserDAO;
import com.totoro.simple.entity.TUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 用户DAO实现
 * Created by Chen on 15-1-5.
 */
@Repository("UserDAOImpl")
public class UserDAOImplImpl extends FrameworkDAOImpl<TUser> implements UserDAO {

    @Override
    public TUser getUserByConditoin(Map<String, Object> condition) {
        List<TUser> list = find(condition);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
