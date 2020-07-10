package com.servlet;

import com.pojo.Cart;
import com.pojo.Product;
import com.pojo.User;
import com.service.CartService;
import com.service.OrdersService;
import com.service.ProductService;
import com.service.serviceImpl.CartServiceImpl;
import com.service.serviceImpl.OrdersServiceImpl;
import com.service.serviceImpl.ProductServiceImpl;
import com.utils.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class CartServlet extends HttpServlet {
    CartService cartService=new CartServiceImpl();
    ProductService productService=new ProductServiceImpl();
    OrdersService ordersService=new OrdersServiceImpl();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path=req.getRequestURI();
        int index=path.lastIndexOf("/");
        String realPath=path.substring(index+1);
        if(realPath.equals("query")){
            query(req,resp);
        } else if (realPath.equals("shopping")) {
            shopping(req,resp);
        }else if (realPath.equals("buy")){
            buy(req,resp);
        }else if(realPath.equals("deleteCart")){
            deleteCart(req,resp);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    protected void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        all(req,resp);
    }
    protected void shopping(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pids=req.getParameter("pid");
        String pquantitys=req.getParameter("pquantity");
        int pid=Integer.parseInt(pids);
        int pquantity=Integer.parseInt(pquantitys);
        HttpSession session=req.getSession();
        User user = (User) session.getAttribute("user");
        Cart cart=new Cart();
        cart.setPid(pid);
        cart.setPquantity(pquantity);
        cart.setUid(user.getUid());
        System.out.println(cart);
        Product products= productService.getProductByPid(pid);
        System.out.println(products);
        if(products.getPstock()<=0) {
            //判断库存的session
            HttpSession session1=req.getSession();
            session1.setAttribute("msg","库存不足！！");
            req.getRequestDispatcher("detail.jsp").forward(req, resp);
        }else {
            productService.updateProduct(pid,pquantity);
            cartService.addCart(cart);
            all(req,resp);
        }
    }
    protected void buy(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        User user = (User) session.getAttribute("user");
        ordersService.addOrders(user.getUid(),user.getUname());
        cartService.deleteCartByUid(user.getUid());
        resp.sendRedirect("shopping-result.jsp");
        //req.getRequestDispatcher("shopping-result.jsp").forward(req,resp);
    }
    protected void all(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        User user = (User) session.getAttribute("user");
        List<Cart> list=cartService.getCartByUid(user.getUid());
        int price,pquantity,total,sum=0;
        for(Cart cart:list){
            price=cart.getProduct().getPrice();
            pquantity=cart.getPquantity();
            total=price*pquantity;
            sum=sum+total;
        }
        req.setAttribute("sum",sum);

        //保存当前页
        int currentPage=0;
        //接受当前页
        String current=req.getParameter("currentPage");
        if(current==null){
            currentPage=1;
        }else {
            currentPage=Integer.parseInt(current);
        }
        Pages pages=new Pages();
        pages.setCurrentPage(currentPage);

        int count = cartService.count();
        System.out.println("count"+count);
        pages.setTotalCount(count);

        System.out.println("pages"+pages);
        //分页查询
        List<Cart> list2 = cartService.getCartByPage(user.getUid(), pages.getCurrentPage(), pages.getPageSize());
        req.setAttribute("pages",pages);
        req.setAttribute("list",list2);
        req.getRequestDispatcher("cart.jsp").forward(req,resp);
    }
    protected void deleteCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入deleteCart");
        String cid1=req.getParameter("cid");
        int cid=Integer.parseInt(cid1);
        System.out.println(cid);
        Cart cart=cartService.getCartByCid(cid);
        cartService.deleteCart(cid);
        System.out.println(cart);
        System.out.println(cart.getPid());

        //更新库存
        productService.updateProduct(cart.getPid(),-cart.getPquantity());
        all(req,resp);
    }
}
