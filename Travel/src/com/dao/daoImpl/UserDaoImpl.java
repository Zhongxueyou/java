package com.dao.daoImpl;

import com.dao.BaseDao;
import com.dao.UserDao;
import com.pojo.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User login(String name, String password) {
        String sql = "SELECT * FROM USER WHERE UNAME=? AND UPASSWORD=?";
        Object params[] = {name, password};
        //测试读取表中的数据
        BaseDao dao = new BaseDao();
        ResultSet rs = dao.query(sql, params);
        User user=null;

        try {
            while (rs.next()) {

                user=new User();
                user.setUid(rs.getInt("uid"));
                user.setUname(rs.getString("uname"));
                user.setUpassword(rs.getString("upassword"));
                user.setRealName(rs.getString("realName"));
                user.setIdentity(rs.getString("identity"));
                user.setSex(rs.getString("sex"));
                user.setPhone(rs.getLong("phone"));

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        close();
        return user;
    }

    @Override
    public void register(User user) {
        String sql="INSERT INTO USER(uname,upassword,realName,identity,sex,phone)"
                +" VALUES(?,?,?,?,?,?)";
        Object params[]={user.getUname(),user.getUpassword(),user.getRealName(),user.getIdentity(),user.getSex(),user.getPhone()};
        update(sql,params);
        close();
    }

    @Override
    public User checkName(String name) {
        String sql = "SELECT * FROM USER WHERE UNAME=?";
        Object params[] = {name};
        //测试读取表中的数据
        BaseDao dao = new BaseDao();
        ResultSet rs = dao.query(sql, params);
        User user=null;

        try {
            while (rs.next()) {
                user=new User();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        close();
        return user;
    }
}
