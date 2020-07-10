package com.service;

import com.pojo.Product;

public interface ProductService {
    Product getProductByPid(int i);
    void updateProduct(int pid,int pquantity);
}
