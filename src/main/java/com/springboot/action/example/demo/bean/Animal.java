package com.springboot.action.example.demo.bean;

public class Animal {
    private String name;
    private int age ;
    private int legNum;

    public Animal(){

    }

    public Animal(String name, int age, int legNum) {
        this.name = name;
        this.age = age;
        this.legNum = legNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getLegNum() {
        return legNum;
    }

    public void setLegNum(int legNum) {
        this.legNum = legNum;
    }
}
