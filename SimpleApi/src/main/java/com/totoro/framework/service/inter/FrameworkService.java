package com.totoro.framework.service.inter;

import java.io.Serializable;

/**
 * 服务层公共接口
 * Created by Chen on 2015/2/12.
 */
public abstract interface FrameworkService<T extends Serializable> {

    public boolean save(T entity);

    public T getById(String id);
}
