package com.filter;

import com.pojo.Product;
import com.service.ProductService;
import com.service.serviceImpl.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class StcokFilter implements Filter {
    ProductService productService=new ProductServiceImpl();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
        System.out.println("----进入StcokFilter----");
        HttpServletRequest request= (HttpServletRequest) arg0;
        HttpServletResponse response= (HttpServletResponse) arg1;
        String pids=arg0.getParameter("pid");
        int pid=Integer.parseInt(pids);
        Product product = productService.getProductByPid(pid);
        HttpSession session=request.getSession();
        session.setAttribute("productsum",product.getPstock());
        arg2.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
