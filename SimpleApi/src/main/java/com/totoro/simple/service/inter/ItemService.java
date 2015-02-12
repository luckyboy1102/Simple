package com.totoro.simple.service.inter;

import com.totoro.framework.service.inter.FrameworkService;
import com.totoro.simple.entity.TItem;

import java.util.List;
import java.util.Map;

/**
 * 物品服务接口
 * Created by Chen on 2015/2/12.
 */
public interface ItemService extends FrameworkService<TItem> {

    /**
     * 根据条件查询物品列表
     * @param condition
     * @return
     */
    public List<TItem> getItemListByCondition(Map<String, Object> condition);
}
