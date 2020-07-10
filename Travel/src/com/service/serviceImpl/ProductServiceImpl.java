package com.service.serviceImpl;

import com.dao.ProductDao;
import com.dao.daoImpl.ProductDaoImpl;
import com.pojo.Product;
import com.service.ProductService;

public class ProductServiceImpl implements ProductService {
    ProductDao productDao=new ProductDaoImpl();

    @Override
    public Product getProductByPid(int i) {
        return productDao.getProductByPid(i);
    }

    @Override
    public void updateProduct(int pid, int pquantity) {
        productDao.updateProduct(pid,pquantity);
    }
}
