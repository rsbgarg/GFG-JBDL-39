package com.example.RedisSample;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RedisHash("Product")
public class Product implements Serializable {

    private Long id;
    private String name;

}
