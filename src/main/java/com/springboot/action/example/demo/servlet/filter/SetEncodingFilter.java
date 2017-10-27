package com.springboot.action.example.demo.servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "SetEncodingFilter", urlPatterns = "/api/*")
public class SetEncodingFilter implements Filter{

    protected FilterConfig filterConfig = null;
    protected String encoding = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init----SetEncodingFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("dofilter----SetEncodingFilter");
        servletRequest.setCharacterEncoding("utf-8");// 解决请求post乱码
        servletResponse.setContentType("text/html;charset=utf-8"); // 响应乱码

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("destroy----SetEncodingFilter");
    }
}
