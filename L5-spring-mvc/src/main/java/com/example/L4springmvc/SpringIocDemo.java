package com.example.L4springmvc;

import com.example.L4springmvc.model.Car;
import com.example.L4springmvc.model.Engine;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIocDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context
                = new ClassPathXmlApplicationContext("projectbeans.xml");
        Engine engine1 = (Engine) context.getBean("engine1");
        Engine engine2 = (Engine) context.getBean("engine2");
        Car car1 = new Car("Bugatti", engine1);
//        car1.startCar();
        System.out.println("Car started " + car1);

    }


}
