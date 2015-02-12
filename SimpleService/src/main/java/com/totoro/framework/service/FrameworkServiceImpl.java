package com.totoro.framework.service;

import com.totoro.framework.dao.inter.FrameworkDAO;
import com.totoro.framework.service.inter.FrameworkService;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * 服务层公共接口实现
 * Created by Chen on 2015/2/12.
 */
@Transactional(readOnly = true)
public abstract class FrameworkServiceImpl<T extends Serializable> implements FrameworkService<T> {

    protected FrameworkDAO<T> frameworkDAO;

    @Override
    @Transactional(readOnly = false)
    public boolean save(T entity) {
        return frameworkDAO.save(entity);
    }

    @Override
    public T getById(String id) {
        return frameworkDAO.getById(id);
    }

    public abstract void setFrameworkDAO(FrameworkDAO<T> frameworkDAO);
}
