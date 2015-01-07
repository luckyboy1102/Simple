package com.totoro.simple.dao;

import org.hibernate.SessionFactory;

import javax.annotation.Resource;

/**
 * Created by Chen on 15-1-6.
 */
public class BaseDAO {

    private SessionFactory sessionFactory;

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
