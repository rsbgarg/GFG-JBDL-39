package com.example.L16KafkaDemo;

import org.springframework.kafka.annotation.KafkaListener;

public class Consumer {

    @KafkaListener(topics = "test-topic", groupId = "idea_group")
    public void listen(String data){
        System.out.println("Message Received " + data);
    }
}
