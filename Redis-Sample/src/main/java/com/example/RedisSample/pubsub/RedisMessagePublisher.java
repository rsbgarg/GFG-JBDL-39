package com.example.RedisSample.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

public class RedisMessagePublisher {

    @Autowired
    private RedisTemplate <String, Object> redisTemplate;

    @Autowired
    private ChannelTopic channelTopic;

    public RedisMessagePublisher(){}

    public RedisMessagePublisher(ChannelTopic channelTopic) {

        this.channelTopic = channelTopic;
    }

    public void publish(String message){
        if(message != null){
            redisTemplate.convertAndSend(channelTopic.getTopic(), message);
        } else{
            redisTemplate.convertAndSend(channelTopic.getTopic(), "Message From" + Thread.currentThread().getName());
        }

    }

}
