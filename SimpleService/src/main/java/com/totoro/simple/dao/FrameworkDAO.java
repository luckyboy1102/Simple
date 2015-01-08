package com.totoro.simple.dao;

/**
 * Created by Chen on 15-1-8.
 */
public interface FrameworkDAO<T> {

    public boolean save(T entity);
}
