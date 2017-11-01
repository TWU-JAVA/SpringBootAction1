package com.springboot.action.example.demo.servlet.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

@Order(3)
@WebFilter
public class SetStaticFileCacheTimeFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        filterChain.doFilter(servletRequest, servletResponse);
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);

        httpServletResponse
                .setDateHeader("Expires", calendar.getTimeInMillis()); // 过期时间一个月
    }

    @Override
    public void destroy() {

    }
}
