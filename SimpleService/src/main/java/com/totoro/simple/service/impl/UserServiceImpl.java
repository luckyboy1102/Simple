package com.totoro.simple.service.impl;

import com.totoro.simple.dao.inter.UserDAO;
import com.totoro.simple.entity.TUser;
import com.totoro.simple.service.inter.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2015/1/7.
 */
@Service("UserServiceImpl")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Resource(name = "UserDAOImpl")
    private UserDAO userDAO;

    @Override
    public boolean login(String userName, String password) {
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("loginName", userName);
        condition.put("password", password);

        return userDAO.getUserByConditoin(condition) != null;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean save(TUser user) {
        return userDAO.save(user);
    }
}
