package com.dao.daoImpl;

import com.dao.BaseDao;
import com.dao.ProductDao;
import com.pojo.Product;
import com.pojo.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDaoImpl extends BaseDao implements ProductDao {
    @Override
    public Product getProductByPid(int i) {
        String sql = "SELECT * FROM PRODUCT WHERE PID=?";
        Object params[] = {i};
        //测试读取表中的数据
        BaseDao dao = new BaseDao();
        ResultSet rs = dao.query(sql, params);
        Product product=null;
        try {
            while (rs.next()) {
                product=new Product();
                product.setPid(rs.getInt("pid"));
                product.setPname(rs.getString("pname"));
                product.setPdescription(rs.getString("pdescription"));
                product.setPrice(rs.getInt("price"));
                product.setPstock(rs.getInt("pstock"));

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        close();
        return product;
    }

    @Override
    public void updateProduct(int pid,int pquantity) {
        String sql="UPDATE PRODUCT SET pstock=pstock-? where pid=?";
        Object params[] = {pquantity,pid};
        update(sql,params);
        close();
    }
}
