package com.dao;

import com.pojo.Product;

public interface ProductDao {
    //根据pid获取产品的信息
    Product getProductByPid(int i);

    //更新产品库存
    void updateProduct(int pid,int pquantity);

}
