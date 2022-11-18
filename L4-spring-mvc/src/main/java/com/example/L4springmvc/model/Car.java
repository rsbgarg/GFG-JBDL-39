package com.example.L4springmvc.model;

public class Car {

    private String name;

    private Engine engine;

    public Car() {
        //tight coupling
//        System.out.println("Creating a blacnk car");
        this.name = "Corvette";
        this.engine = new Engine("V6", 5000);
    }

    //loose coupling
    public Car(String name, Engine engine) {
        this.name = name;
        this.engine = engine;
    }

    public void startCar(){
        System.out.println("Staring car " + name
                + " with Engine " + engine.getName());
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", engine=" + engine +
                '}';
    }


}
