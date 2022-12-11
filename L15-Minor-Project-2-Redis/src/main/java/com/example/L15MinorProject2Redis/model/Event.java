package com.example.L15MinorProject2Redis.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Setter
@Entity
public class Event implements Serializable {

    @Id
    Long id;
    String name;
    String timestamp;
    String user;

}
