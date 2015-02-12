package com.totoro.framework.dao.impl;

import com.totoro.framework.dao.inter.FrameworkDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * DAO通用方法
 * Created by Chen on 15-1-6.
 */
public abstract class FrameworkDAOImpl<T extends Serializable> implements FrameworkDAO<T> {

    private SessionFactory sessionFactory;

    // 日志
    protected final Log logger = LogFactory.getLog(getClass());
    // 实体对象类
    protected Class<T> entityClass;
    // 实体对象名称
    protected String entityClassName;

    /**
     * 获取实体类对象
     */
    @SuppressWarnings("unchecked")
    public FrameworkDAOImpl() {
        if ((getClass().getGenericSuperclass() instanceof ParameterizedType)) {
            this.entityClass = (Class)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        } else {
            this.entityClass = (Class)((ParameterizedType)((Class) getClass().getGenericSuperclass()).getGenericSuperclass()).getActualTypeArguments()[0];
        }
        this.entityClassName = entityClass.getSimpleName();
    }

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

    @Override
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

    @Override
    public T getById(String id) {
        return (T) getSession().get(entityClass, id);
    }

    /**
     * 查询符合条件的实体结果集
     * @param hql 进行查询的HQL语句
     * @param objects 查询参数
     * @return 符合条件的实体集合
     */
    @SuppressWarnings("unchecked")
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

    /**
     * 根据Map中的键值对进行查询
     * 需保证键为实体中的变量名
     * @param map
     * @return
     */
    public List<T> find(Map<String, Object> map) {
        String objectName = entityClassName.toLowerCase();
        StringBuilder hql = new StringBuilder("from " + entityClassName + " " + objectName);

        if (map.size() > 0) {
            hql.append(" where ");
            List<String> columns = new ArrayList<String>();
            columns.addAll(map.keySet());

            for (int i = 0; i < columns.size(); i++) {
                if (i != 0) {
                    hql.append("and ");
                }
                hql.append(objectName + "." + columns.get(i) + " = ? ");
            }
        }

        return find(hql.toString(), map.values().toArray());
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
