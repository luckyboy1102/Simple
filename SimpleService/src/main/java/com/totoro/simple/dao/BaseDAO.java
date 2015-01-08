package com.totoro.simple.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;

import javax.annotation.Resource;

/**
 * Created by Chen on 15-1-6.
 */
public class BaseDAO<T> implements FrameworkDAO<T> {

    private SessionFactory sessionFactory;

    // 日志
    protected final Log logger = LogFactory.getLog(getClass());

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public Session getSession() {
        try {
            return this.getSessionFactory().getCurrentSession();
        } catch (HibernateException e) {
            if (this.logger.isErrorEnabled()) {
                this.logger.error("获取session失败！", e);
            }
            return null;
        }
    }

    public boolean save(T entity) {
        boolean flag;
        Assert.assertNotNull(entity);
        try {
            getSession().saveOrUpdate(entity);
            flag = true;
        } catch (HibernateException e) {
            if (this.logger.isErrorEnabled()) {
                this.logger.error("添加或更新失败", e);
            }
            flag = false;
        }
        return flag;
    }
}
