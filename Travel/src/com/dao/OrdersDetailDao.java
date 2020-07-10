package com.dao;

import com.pojo.OrdersDetail;

import java.util.List;

public interface OrdersDetailDao {
    //添加到订单详细信息表
    void addOrdersDetails(OrdersDetail ordersDetail);

    //获取订单表的每条信息
    List<OrdersDetail> getOrdersDetails(int oid);
}
