package com.example.L4springmvc;

import com.example.L4springmvc.model.Car;
import com.example.L4springmvc.model.Engine;

public class DIDemo {

    public static void main(String[] args) {

        Car car1 = new Car();
        car1.startCar();

        Engine engine1 =  new Engine("V8", 10000);
        Engine engine2 = new Engine("Muscle-Engine", 8000);

        Car car2 = new Car("Ferrari Spider", engine2);
        car2.startCar();

    }
}
