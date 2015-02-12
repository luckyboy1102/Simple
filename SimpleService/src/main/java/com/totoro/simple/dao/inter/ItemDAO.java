package com.totoro.simple.dao.inter;

import com.totoro.framework.dao.inter.FrameworkDAO;
import com.totoro.simple.entity.TItem;

import java.util.List;
import java.util.Map;

/**
 * 物品DAO接口
 * Created by Chen on 2015/2/12.
 */
public interface ItemDAO extends FrameworkDAO<TItem> {

    public List<TItem> getItemListByCondition(Map<String, Object> condition);
}
