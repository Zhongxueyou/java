package com.dao;

import com.pojo.Cart;

import java.util.List;

public interface CartDao {
    //添加到购物车
    void addCart(Cart cart);

    //获取一条购物车信息
    Cart getCartByCid(int i);

    //获得购物车订单总数
    int count();

    //分页查询订单
    List<Cart> getCartByPage(int uid,int start,int pageSize);

    //结账后清除购物车
    void deleteCart(int i);

    //删除购物车里指定订单
    void deleteCartByUid(int i);

    //遍历购物车订单
    List<Cart> getAllPrice();

    //获取用户的全部订单
    List<Cart> getCartByUid(int uid);
}
