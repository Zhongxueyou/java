package com.servlet;

import com.pojo.User;
import com.service.UserService;
import com.service.serviceImpl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UserServlet extends HttpServlet {
    private UserService userService=new UserServiceImpl();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String path=req.getRequestURI();
        int index=path.lastIndexOf("/");
        String realPath=path.substring(index+1);
        if(realPath.equals("login")){
            login(req,resp);
        }else if(realPath.equals("register")){
            System.out.println("==========");
            register(req,resp);
        }else if(realPath.equals("checkName")){
            checkName(req,resp);
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        doGet(req,resp);
    }
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String name=req.getParameter("name");
        String password=req.getParameter("password");
        System.out.println(name);
        System.out.println(password);
        User user=userService.login(name,password);
        System.out.println(user);
        if(user!=null){
            HttpSession session=req.getSession();
            session.setAttribute("user",user);
           req.getRequestDispatcher("index.jsp").forward(req,resp);
        }
        else {
            req.setAttribute("msg","用户名或密码错误");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }
    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        System.out.println("-----------------------");
        String name=req.getParameter("name");
        String password=req.getParameter("password");
        String realName=req.getParameter("realName");
        String identity=req.getParameter("identity");
        String sex=req.getParameter("sex");
        String phone1=req.getParameter("phone");
        String textfield=req.getParameter("textfield");
        String generatedCode= (String) req.getSession().getAttribute("verityCode");
        Long phone=Long.parseLong(phone1);
        if(textfield.toLowerCase().equals(generatedCode.toLowerCase())){
            User user=new User(name,password,realName,identity,sex,phone);
            System.out.println(user);
            userService.register(user);
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }else {
            req.setAttribute("msg","验证码错误");
            req.getRequestDispatcher("regist.jsp").forward(req,resp);
        }
    }
    protected void checkName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String name=req.getParameter("name");
        PrintWriter writer=resp.getWriter();
        System.out.println("name:"+name);
        String res="0";
        User user=userService.checkName(name);
        if(user!=null){
            res="1";
        }else {
            res="0";
        }
        writer.write(res);
    }
}
