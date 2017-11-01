package com.springboot.action.example.demo.servlet.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(2)
@WebFilter
public class ForceNoCacheFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /*
      Expires数据头：值为GMT时间值，为-1指浏览器不要缓存页面。
      Cache-Control响应头有两个常用值。
      no-cache指浏览器不要缓存当前页面。
      max-age:xxx指浏览器缓存页面xxx秒
     */

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        ((HttpServletResponse) servletResponse).setHeader("Cache-Control","no-cache");
        ((HttpServletResponse) servletResponse).setHeader("Pragma","no-cache");
        ((HttpServletResponse) servletResponse).setDateHeader ("Expires", -1);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
