package com.totoro.simple.service.impl;

import com.totoro.framework.dao.inter.FrameworkDAO;
import com.totoro.framework.service.FrameworkServiceImpl;
import com.totoro.simple.dao.inter.ItemDAO;
import com.totoro.simple.entity.TItem;
import com.totoro.simple.service.inter.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 物品服务接口实现
 * Created by Chen on 2015/2/12.
 */
@Service("ItemServiceImpl")
@Transactional(readOnly = true)
public class ItemServiceImpl extends FrameworkServiceImpl<TItem> implements ItemService {

    @Resource(name = "ItemDAOImpl")
    private ItemDAO itemDAO;

    @Override
    public List<TItem> getItemListByCondition(Map<String, Object> condition) {
        return itemDAO.getItemListByCondition(condition);
    }

    @Override
    @Resource(name = "ItemDAOImpl")
    public void setFrameworkDAO(FrameworkDAO frameworkDAO) {
        this.frameworkDAO = itemDAO;
    }
}
