package com.dao.daoImpl;

import com.dao.BaseDao;
import com.dao.OrdersDao;
import com.pojo.Orders;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersDaoImpl extends BaseDao implements OrdersDao {
    @Override
    public void addOrders(Orders orders) {
        String sql="INSERT INTO ORDERS(UID,UNAME,OCREATETIME,OCOST)"
                +" VALUES(?,?,?,?)";
        Object params[]={orders.getUid(),orders.getUname(),orders.getOcreatetime(),orders.getOcost()};
        update(sql,params);
        close();
    }

    @Override
    public int getOrdersByUid(int i) {
        String sql="SELECT * FROM ORDERS WHERE uid=?";
        Object params[] ={i};
        int oid=0;
        ResultSet rs=query(sql,params);
        try {
            if(rs.last()){
                oid=rs.getInt("oid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return oid;
    }

    @Override
    public Orders getMyOrdersByUid(int uid) {
        String sql="SELECT * FROM ORDERS WHERE uid=?";
        Object params[] ={uid};
        Orders orders=null;
        ResultSet rs=query(sql,params);
        try {
            if(rs.last()){
                orders=new Orders();
                orders.setOid(rs.getInt("oid"));
                orders.setOcreatetime(rs.getDate("ocreatetime"));
                orders.setOcost(rs.getInt("ocost"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        close();
        return orders;
    }

}
