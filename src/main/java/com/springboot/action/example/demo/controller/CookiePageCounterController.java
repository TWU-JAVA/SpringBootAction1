package com.springboot.action.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CookiePageCounterController {

    @RequestMapping("/cookieCheck")
    public String counter(@CookieValue(value = "hitCounter",defaultValue = "0") Long hitCounter, HttpServletResponse response, Model model){
        hitCounter++;
        Cookie cookie = new Cookie("hitCounter", hitCounter.toString());
        response.addCookie(cookie);
        model.addAttribute("hitCounter",hitCounter);
        cookie.setMaxAge(300);
        return "cookieCounter";
    }
}
