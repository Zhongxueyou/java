package com.service;

import com.pojo.OrdersDetail;

import java.util.List;

public interface OrdersDetailService {
    List<OrdersDetail> getOrdersDetails(int oid);
}
