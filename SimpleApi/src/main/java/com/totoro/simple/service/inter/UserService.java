package com.totoro.simple.service.inter;

import com.totoro.framework.service.inter.FrameworkService;
import com.totoro.simple.entity.TUser;

/**
 * 用户服务接口
 * Created by Chen on 15-1-5.
 */
public interface UserService extends FrameworkService<TUser> {

    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    public boolean login(String userName, String password);
}

