package com.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodinigFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    //处理post请求中文乱码
    public void doFilter(ServletRequest arg0, ServletResponse arg1,
                         FilterChain arg2) throws IOException, ServletException {
        System.out.println("---中文乱码问题----");

        //修改字符集
        arg0.setCharacterEncoding("utf-8");
        arg1.setCharacterEncoding("utf-8");

        //请求向下传递
        arg2.doFilter(arg0, arg1);

    }

    @Override
    public void destroy() {

    }
}
