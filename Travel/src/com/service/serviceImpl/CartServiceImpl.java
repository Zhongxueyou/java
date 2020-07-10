package com.service.serviceImpl;

import com.dao.CartDao;
import com.dao.daoImpl.CartDaoImpl;
import com.pojo.Cart;
import com.service.CartService;

import java.util.List;

public class CartServiceImpl implements CartService {
    CartDao cartDao=new CartDaoImpl();
    @Override
    public List<Cart> getAllPrice() {
        return cartDao.getAllPrice();
    }

    @Override
    public void addCart(Cart cart) {
        cartDao.addCart(cart);
    }

    @Override
    public List<Cart> getCartByUid(int uid) {
        return cartDao.getCartByUid(uid);
    }

    @Override
    public void deleteCartByUid(int i) {
        cartDao.deleteCartByUid(i);
    }

    @Override
    public void deleteCart(int i) {
        cartDao.deleteCart(i);
    }

    @Override
    public Cart getCartByCid(int i) {
        return cartDao.getCartByCid(i);
    }

    @Override
    public int count() {
        return cartDao.count();
    }

    @Override
    public List<Cart> getCartByPage(int uid, int currentPage, int pageSize) {
        int start=(currentPage-1)*pageSize;
        return cartDao.getCartByPage(uid,start,pageSize);
    }


}
