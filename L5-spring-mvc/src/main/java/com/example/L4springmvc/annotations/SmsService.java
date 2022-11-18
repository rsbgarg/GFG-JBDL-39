package com.example.L4springmvc.annotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SmsService {

    @Autowired
    SmsProvider smsProvider;

//    public SmsService(SmsProvider smsProvider) {
//        this.smsProvider = smsProvider;
//    }

    public void sendSms(String text){
        smsProvider.sendSms(text);
    }

}
