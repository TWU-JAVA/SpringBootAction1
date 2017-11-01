package com.springboot.action.example.demo.controller;

import com.springboot.action.example.demo.bean.Animal;
import com.springboot.action.example.demo.service.AnimalService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/animal")
public class AnimalLoginController{
    private AnimalService animalService;

    public AnimalLoginController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("animal", new Animal("dog",1,2));
        return modelAndView;
    }

    @RequestMapping(value = "/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("animalLogin");

        modelAndView.addObject("animal", new Animal("dog",1,2));
        return modelAndView;
    }

    @RequestMapping(value = "/loginProcess",method = RequestMethod.POST)
    public String loginSuccess(@RequestParam(required = true) String animalName, @RequestParam(required = true) int animalAge, HttpServletRequest request){
        String result = "animalLogin";
        boolean loginSuccessFlag = false;

        Animal animal = animalService.findAnimalByName(animalName);
        if (animal.getAge() == animalAge && animal.getName() != null) {
            loginSuccessFlag = true;
            result = "index";
        }else{
            animal = null;
        }
        if(loginSuccessFlag){
            request.getSession().setAttribute("_session_animal", animal);
        }
        return result;
    }

}
