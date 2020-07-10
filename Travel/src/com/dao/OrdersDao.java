package com.dao;

import com.pojo.Orders;

public interface OrdersDao {
    //添加订单
    void addOrders(Orders orders);

    //根据用户id获取订单oid
    int getOrdersByUid(int i);

    //根据用户id获取最后一条订单
    Orders getMyOrdersByUid(int uid);
}
