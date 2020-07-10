package com.dao;

import java.sql.*;

//操作数据库的工具类
public class BaseDao {
    //创造sql的接口对象
    private Connection connection;//数据连接
    private PreparedStatement statement;//表示执行sql语句的对象
    private ResultSet rs;//表示保持返回结果的对象


    //该方法用户获取数据库链接
    public boolean getConnection() {
        boolean flag=false;//表示数据库连接失败
        try {
            //1.加载mysql数据库的驱动类
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取链接对象
            String url = "jdbc:mysql://localhost:3306/tour";
            String user = "root";
            String password = "123456";
            connection = DriverManager.getConnection(url, user, password);
            //System.out.println("connect:"+connection);
            if(connection!=null){
                //System.out.println("数据库连接成功");
                flag=true;
            }
            else
                System.out.println("数据库连接失败");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    public ResultSet query(String sql,Object params[]) {
        try {
            if (getConnection()) {// 1.调用getConnection()方法，2.利用该方法的返回值，如果返回值是true的话，则进入if里面进行执行
                statement = connection.prepareStatement(sql);
                for (int i = 0; i < params.length; i++) {
                    statement.setObject(i + 1, params[i]);
                }
                rs = statement.executeQuery();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }
    public void update(String sql,Object params[]) {
        int j=0;
        try {
            if (getConnection()) {
                statement = connection.prepareStatement(sql);
                for(int i=0;i<params.length;i++){
                    statement.setObject(i+1, params[i]);
                }
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //关闭资源 先开后关，后开先关
    public void close() {
        try {
            if (rs != null) {
                rs.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}

