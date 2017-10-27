package com.springboot.action.example.demo.servlet.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListenerOnlineCount implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        ServletContext servletContext = httpSessionEvent.getSession().getServletContext();
        Integer onlineCount = (Integer) servletContext.getAttribute("onlineCount");
        if (onlineCount == null) {
            servletContext.setAttribute("onlineCount", 1);
        }else{
            onlineCount++;
            servletContext.setAttribute("onlineCount", onlineCount);
        }

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        ServletContext servletContext = httpSessionEvent.getSession().getServletContext();
        Integer onlineCount = (Integer) servletContext.getAttribute("onlineCount");
        if (onlineCount == null) {
            servletContext.setAttribute("onLineCount", 1);
        }else{
            onlineCount--;
            servletContext.setAttribute("onLineCount", onlineCount);
        }
    }
}
