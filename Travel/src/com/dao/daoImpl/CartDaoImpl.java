package com.dao.daoImpl;

import com.dao.BaseDao;
import com.dao.CartDao;
import com.pojo.Cart;
import com.pojo.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl extends BaseDao implements CartDao {
    @Override
    public void addCart(Cart cart) {
        String sql="INSERT INTO CART(PID,PQUANTITY,UID)"
                +" VALUES(?,?,?)";
        Object params[]={cart.getPid(),cart.getPquantity(),cart.getUid()};
        update(sql,params);
        close();
    }

    @Override
    public Cart getCartByCid(int i) {
        String sql = "SELECT * FROM CART where cid=?";
        Object params[] = {i};
        BaseDao dao = new BaseDao();
        ResultSet rs = dao.query(sql, params);
        Cart cart=null;
        try {
            while (rs.next()) {
                cart=new Cart();
                cart.setPid(rs.getInt("pid"));
                cart.setPquantity(rs.getInt("pquantity"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        close();
        return cart;
    }

    @Override
    public int count() {
        String sql = "SELECT count(*) as c FROM CART";
        Object params[] = {};
        BaseDao dao = new BaseDao();
        ResultSet rs = dao.query(sql, params);
        int c=0;
        try {
            while (rs.next()){
                c = rs.getInt("c");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }

    @Override
    public List<Cart> getCartByPage(int uid, int start, int pageSize) {
        String sql = "SELECT c.cid,c.pquantity,p.pname,p.price FROM CART c,PRODUCT p where c.pid=p.pid and uid=? limit ?,?";
        Object params[] = {uid,start,pageSize};
        List<Cart> list=new ArrayList<Cart>();
        //测试读取表中的数据
        BaseDao dao = new BaseDao();
        ResultSet rs = dao.query(sql, params);
        Cart cart=null;
        Product product=null;
        try {
            while (rs.next()) {
                cart=new Cart();
                product=new Product();
                cart.setCid(rs.getInt("cid"));
                cart.setPquantity(rs.getInt("pquantity"));
                product.setPname(rs.getString("pname"));
                product.setPrice(rs.getInt("price"));
                cart.setProduct(product);
                list.add(cart);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        close();
        return list;
    }


    @Override
    public void deleteCart(int i) {
        String sql = "DELETE  FROM CART WHERE CID=?";
        Object params[] = {i};
        //测试读取表中的数据
        BaseDao dao = new BaseDao();
        dao.update(sql, params);
        close();

    }

    @Override
    public void deleteCartByUid(int i) {
        String sql = "DELETE  FROM CART WHERE UID=?";
        Object params[] = {i};
        //测试读取表中的数据
        BaseDao dao = new BaseDao();
        dao.update(sql, params);
        close();
    }

    @Override
    public List<Cart> getAllPrice() {
        String sql = "SELECT c.cid,c.pquantity,p.pname,p.price FROM CART c,PRODUCT p where c.pid=p.pid";
        Object params[] = {};
        List<Cart> list=new ArrayList<Cart>();
        //测试读取表中的数据
        BaseDao dao = new BaseDao();
        ResultSet rs = dao.query(sql, params);
        Cart cart=null;
        Product product=null;
        try {
            while (rs.next()) {
                cart=new Cart();
                product=new Product();
                cart.setCid(rs.getInt("cid"));
                cart.setPquantity(rs.getInt("pquantity"));
                product.setPname(rs.getString("pname"));
                product.setPrice(rs.getInt("price"));
                cart.setProduct(product);
                list.add(cart);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        close();
        return list;
    }

    @Override
    public List<Cart> getCartByUid(int uid) {
        String sql = "SELECT c.cid,c.pid,c.uid,c.pquantity,p.pname,p.price FROM CART c,PRODUCT p where c.pid=p.pid and c.uid=?";
        Object params[] = {uid};
        List<Cart> list=new ArrayList<Cart>();
        //测试读取表中的数据
        BaseDao dao = new BaseDao();
        ResultSet rs = dao.query(sql, params);
        Cart cart=null;
        Product product=null;
        try {
            while (rs.next()) {
                cart=new Cart();
                product=new Product();
                cart.setPid(rs.getInt("pid"));
                cart.setCid(rs.getInt("cid"));
                cart.setUid(rs.getInt("uid"));
                cart.setPquantity(rs.getInt("pquantity"));
                product.setPname(rs.getString("pname"));
                product.setPrice(rs.getInt("price"));
                cart.setProduct(product);
                list.add(cart);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        close();
        return list;
    }
}
