package com.kevinyin.lcollection;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Kevin_Yin on 2017/7/4.
 */
public class TestMap {

    ConcurrentHashMap<String, ConcurrentHashMap<String, Bean>> maps = new ConcurrentHashMap<String, ConcurrentHashMap<String, Bean>>();

    public void put(String storeId, Bean bean) {
        ConcurrentHashMap m = maps.get(storeId);
        if (m == null) {
            m = new ConcurrentHashMap();
            maps.put(storeId, m);
        }
        maps.get(storeId).put(bean.getOrderId(), bean);
    }

    public void replace() {

    }


}
