package com.filter;

import com.dao.OrdersDao;
import com.pojo.Orders;
import com.pojo.OrdersDetail;
import com.pojo.User;
import com.service.OrdersDetailService;
import com.service.OrdersService;
import com.service.serviceImpl.OrdersDetailServiceImpl;
import com.service.serviceImpl.OrdersServiceImpl;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class BuyFilter implements Filter {
    OrdersService ordersService=new OrdersServiceImpl();
    OrdersDetailService ordersDetailService=new OrdersDetailServiceImpl();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
        System.out.println("-------进入BuyFilter--------");
        HttpServletRequest request= (HttpServletRequest) arg0;
        HttpServletResponse response= (HttpServletResponse) arg1;
        HttpSession session=request.getSession();
        User user = (User) session.getAttribute("user");
        Orders orders = ordersService.getMyOrdersByUid(user.getUid());
        List<OrdersDetail> list = ordersDetailService.getOrdersDetails(orders.getOid());
        System.out.println("list"+list);
        session.setAttribute("orders",orders);
        session.setAttribute("list",list);
        System.out.println(session.getAttribute("orders"));
        arg2.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
