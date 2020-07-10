package com.service.serviceImpl;

import com.dao.CartDao;
import com.dao.OrdersDao;
import com.dao.OrdersDetailDao;
import com.dao.daoImpl.CartDaoImpl;
import com.dao.daoImpl.OrdersDaoImpl;
import com.dao.daoImpl.OrdersDetailDaoImpl;
import com.pojo.Cart;
import com.pojo.Orders;
import com.pojo.OrdersDetail;
import com.service.OrdersService;

import java.util.Date;
import java.util.List;

public class OrdersServiceImpl implements OrdersService {
    OrdersDao ordersDao=new OrdersDaoImpl();
    CartDao cartDao=new CartDaoImpl();
    OrdersDetailDao ordersDetailDao=new OrdersDetailDaoImpl();
    @Override
    public void addOrders(int uid, String uname) {
        List<Cart> list=cartDao.getCartByUid(uid);
        int total=0;
        for(Cart cart:list){
            int price=cart.getProduct().getPrice();
            int pquantity=cart.getPquantity();
            int sum=price*pquantity;
            total=sum+total;
        }
        Orders orders=new Orders();
        orders.setUid(uid);
        orders.setUname(uname);
        orders.setOcreatetime(new Date());
        orders.setOcost(total);
        if(total==0){

        }else {
            ordersDao.addOrders(orders);
        }

        //获取当前用户在订单表中的记录主键
        int oid=ordersDao.getOrdersByUid(uid);
        OrdersDetail detail=new OrdersDetail();
        detail.setOid(oid);
        for(Cart cart:list){
            int price=cart.getProduct().getPrice();
            int pquantity=cart.getPquantity();
            int sum=price*pquantity;
            String pname=cart.getProduct().getPname();
            int pid=cart.getPid();
            detail.setPquantity(pquantity);
            detail.setPid(pid);
            detail.setPrice(sum);
            detail.setPname(pname);
            ordersDetailDao.addOrdersDetails(detail);

        }
    }

    @Override
    public Orders getMyOrdersByUid(int uid) {
        return ordersDao.getMyOrdersByUid(uid);
    }
}
