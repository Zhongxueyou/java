package com.dao.daoImpl;

import com.dao.BaseDao;
import com.dao.OrdersDetailDao;
import com.pojo.Cart;
import com.pojo.OrdersDetail;
import com.pojo.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersDetailDaoImpl extends BaseDao implements OrdersDetailDao {
    @Override
    public void addOrdersDetails(OrdersDetail ordersDetail) {
        String sql="INSERT INTO orders_detail(oid,pid,pquantity,pname,price)"
                +" VALUES(?,?,?,?,?)";
        Object params[]={ordersDetail.getOid(),ordersDetail.getPid(),ordersDetail.getPquantity(),ordersDetail.getPname(),ordersDetail.getPrice()};
        update(sql,params);
        close();

    }

    @Override
    public List<OrdersDetail> getOrdersDetails(int oid) {
        String sql = "SELECT * FROM orders_detail where oid=?";
        Object params[] = {oid};
        List<OrdersDetail> list=new ArrayList<OrdersDetail>();
        //测试读取表中的数据
        BaseDao dao = new BaseDao();
        ResultSet rs = dao.query(sql, params);
        OrdersDetail ordersDetail=null;
        try {
            while (rs.next()) {
                ordersDetail=new OrdersDetail();
                ordersDetail.setPname(rs.getString("pname"));
                ordersDetail.setPrice(rs.getInt("price"));
                ordersDetail.setPquantity(rs.getInt("pquantity"));
                list.add(ordersDetail);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        close();
        return list;
    }
}
