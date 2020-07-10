package com.service.serviceImpl;

import com.dao.OrdersDetailDao;
import com.dao.daoImpl.OrdersDetailDaoImpl;
import com.pojo.OrdersDetail;
import com.service.OrdersDetailService;

import java.util.List;

public class OrdersDetailServiceImpl implements OrdersDetailService {
    OrdersDetailDao ordersDetailDao=new OrdersDetailDaoImpl();
    @Override
    public List<OrdersDetail> getOrdersDetails(int oid) {
        return ordersDetailDao.getOrdersDetails(oid);
    }
}
