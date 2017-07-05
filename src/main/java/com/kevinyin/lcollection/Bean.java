package com.kevinyin.lcollection;

/**
 * Created by Kevin_Yin on 2017/7/4.
 */
public class Bean {
    private String orderId;
    private String name;

    public Bean(String orderId, String name) {
        this.orderId = orderId;
        this.name = name;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
