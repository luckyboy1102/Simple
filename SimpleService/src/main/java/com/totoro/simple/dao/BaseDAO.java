package com.totoro.simple.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.Collections;
import java.util.List;

/**
 * DAO通用方法
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

    /**
     * 查询符合条件的实体结果集
     * @param hql 进行查询的HQL语句
     * @param objects 查询参数
     * @return 符合条件的实体集合
     */
    public List<T> find(String hql, Object[] objects) {
        List<T> data = null;
        try {
            Query query = getSession().createQuery(hql);
            if (objects != null) {
                setParameters(query, objects);
            }
            data = query.list();
        } catch (HibernateException e) {
            if (this.logger.isErrorEnabled()) {
                this.logger.error("查询符合条件的实体结果集失败！", e);
            }
        }
        return data == null ? Collections.<T> emptyList() : data;
    }

    private void setParameters(Query query, Object[] paramlist) {
        if (paramlist != null) {
            for (int i = 0; i < paramlist.length; i++) {
                if ((paramlist[i] instanceof Date)) {
                    query.setTimestamp(i, (Date) paramlist[i]);
                } else {
                    query.setParameter(i, paramlist[i]);
                }
            }
        }
    }
}
