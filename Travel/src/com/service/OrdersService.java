package com.service;

import com.pojo.Orders;

public interface OrdersService {
    void addOrders(int uid,String uname);
    Orders getMyOrdersByUid(int uid);
}
