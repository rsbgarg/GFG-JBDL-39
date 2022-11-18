package com.example.L4springmvc.annotations;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class SmsProvider {

    String network;



    public SmsProvider() {
        this.network = "Jio";
        System.out.println("Smsprovider created with netowork" + this.network);
    }

    public SmsProvider(String network){
        this.network = network;
       System.out.println("Smsprovider created with netowork" + this.network);
    }

    public void sendSms(String message){
        System.out.println("Sending sms with text " + message);
    }



    @Override
    public String toString() {
        return "SmsProvider{" +
                "network='" + network + '\'' +
                '}';
    }
}
