package com.totoro.simple.dao.impl;

import com.totoro.framework.dao.impl.FrameworkDAOImpl;
import com.totoro.simple.dao.inter.ItemDAO;
import com.totoro.simple.entity.TItem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 物品DAO实现
 * Created by Chen on 2015/2/12.
 */
@Repository("ItemDAOImpl")
public class ItemDAOImpl extends FrameworkDAOImpl<TItem> implements ItemDAO {

    public List<TItem> getItemListByCondition(Map<String, Object> condition) {
        return find(condition);
    }
}
