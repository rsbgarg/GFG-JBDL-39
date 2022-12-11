package com.example.L15MinorProject2Redis.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Data
@RedisHash("Event")
public class EventDTO implements Serializable {

    private Long id;
    private String name;
    private String timestamp;
    private String user;
}
