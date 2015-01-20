package com.totoro.simple.dao.impl;

import com.totoro.simple.dao.BaseDAO;
import com.totoro.simple.dao.inter.UserDAO;
import com.totoro.simple.entity.TUser;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Chen on 15-1-5.
 */
@Repository("UserDAOImpl")
public class UserDAOImpl extends BaseDAO<TUser> implements UserDAO {

    @Override
    public TUser getUserByConditoin(Map<String, Object> condition) {
        StringBuilder hql = new StringBuilder("from TUser user where");
        List<Object> param = new ArrayList<Object>();

        Iterator<Map.Entry<String, Object>> iterator = condition.entrySet().iterator();
        Map.Entry<String, Object> entry;
        while (iterator.hasNext()) {
            entry = iterator.next();
            hql.append(" " + entry.getKey()).append(" = ? ");
            param.add(entry.getValue());
        }

        List<TUser> list = find(hql.toString(), param.toArray());
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
