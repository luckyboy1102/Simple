package com.totoro.simple.service.impl;

import com.totoro.simple.entity.TItem;
import com.totoro.simple.service.inter.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:applicationContext.xml",
        "classpath:applicationContext-simple.xml",
        "classpath:service-provider-simple.xml"
})
public class ItemServiceImplTest {

    @Resource(name = "ItemServiceImpl")
    private ItemService itemService;

    @Test
    public void testGetItemListByCondition() throws Exception {
        Map<String, Object> condition = new HashMap<String, Object>();
        List<TItem> list = itemService.getItemListByCondition(condition);
        assertTrue(list.size() > 0);
    }

    @Test
    public void testSaveItem() {
        TItem item;
        for (int i = 0; i < 10; i++) {
            BigDecimal b = new BigDecimal(5 + (float) (Math.random() * ((1000 - 5) + 1)));

            item = new TItem();
            item.setName("Item" + i);
            item.setPrice(b.floatValue());
            itemService.save(item);
        }
    }
}