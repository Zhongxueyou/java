package com.service;

import com.pojo.Cart;
import com.pojo.Product;

import java.util.List;

public interface CartService {
    List<Cart> getAllPrice();
    void addCart(Cart cart);
    List<Cart> getCartByUid(int uid);
    void deleteCartByUid(int i);
    void deleteCart(int i);
    Cart getCartByCid(int i);
    //获得购物车订单总数
    int count();

    //分页查询订单
    List<Cart> getCartByPage(int uid,int currentPage,int pageSize);
}
