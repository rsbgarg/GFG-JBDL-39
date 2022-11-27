package com.example.RedisSample.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublisherService {

    @Autowired
    RedisMessagePublisher redisMessagePublisher;

    @PostMapping("/publish")
    public void publishMessage(MessageObject message){
        redisMessagePublisher.publish(message.getMessage());
    }
}
