package com.totoro.framework.dao.inter;

import java.io.Serializable;

/**
 * 通用DAO接口
 * Created by Chen on 15-1-8.
 */
public abstract interface FrameworkDAO<T extends Serializable> {

    public boolean save(T entity);

    public T getById(String id);
}
