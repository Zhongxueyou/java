package com.filter;

import com.pojo.User;
import com.service.ProductService;
import com.service.serviceImpl.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    ProductService productService=new ProductServiceImpl();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
        System.out.println("---用户验证拦截----");
        HttpServletRequest request= (HttpServletRequest) arg0;
        HttpServletResponse response= (HttpServletResponse) arg1;
        HttpSession session=request.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        if(user!=null){
            arg2.doFilter(request,response);
        }else {
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }
    }

    @Override
    public void destroy() {

    }
}
