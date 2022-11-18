package com.example.L4springmvc.model;

public class Engine {

    private String name;
    private Integer cc;

    public Engine() {
    }

    public Engine(String name, Integer cc) {
        System.out.println("Creating a new Engine Name: "+ name);
        this.name = name;
        this.cc = cc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCc() {
        return cc;
    }

    public void setCc(Integer cc) {
        this.cc = cc;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "name='" + name + '\'' +
                ", cc=" + cc +
                '}';
    }
}
