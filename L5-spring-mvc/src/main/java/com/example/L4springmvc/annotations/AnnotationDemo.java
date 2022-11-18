package com.example.L4springmvc.annotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.example.L4springmvc.annotation")
public class AnnotationDemo {


//    @Bean
//    @Scope("prototype")
//    public SmsProvider smsProviderBean(){
//        return new SmsProvider("Airtel");
//    }

//       @Bean("smsService")
//    public SmsService smsService(){
//        return new SmsService(new SmsProvider());
//    }

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext("com.gfg.demo.annotations");

        SmsService smsService =
                (SmsService) context.getBean("smsService");

        smsService.sendSms("OTP: 8687");
    }
}
