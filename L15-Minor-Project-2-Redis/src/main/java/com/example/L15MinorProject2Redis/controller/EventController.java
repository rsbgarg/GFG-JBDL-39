package com.example.L15MinorProject2Redis.controller;

import com.example.L15MinorProject2Redis.model.Event;
import com.example.L15MinorProject2Redis.model.EventDTO;
import com.example.L15MinorProject2Redis.redis.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController("/event")
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    private RedisTemplate<String, EventDTO> eventDTORedisTemplate;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @PostMapping("/collectEvent")
    ResponseEntity<EventDTO> collectEvent(@RequestBody EventDTO event){
        incrementEventCount(event);
        eventDTORedisTemplate.opsForList().rightPush("EventList", event);
        return ResponseEntity.ok().build();
    }

    private void incrementEventCount(EventDTO event) {
        String name = event.getName();
        eventDTORedisTemplate.opsForValue().increment(name);
    }

    @GetMapping("/getEventCount")
    ResponseEntity<Integer> getEventCount(@RequestParam String name){
        Integer count = Integer.parseInt(Optional.of(redisTemplate.opsForValue().get(name)).orElse("0"));
        //save in DB
        return ResponseEntity.ok().body(count);
    }



    @GetMapping("/processEvent")
    ResponseEntity<EventDTO> processEvent(){
        EventDTO eventDTO = eventDTORedisTemplate.opsForList().leftPop("EventList");
        //save in DB
        //TODO

        //TODO last user events cache

        return ResponseEntity.ok().build();
    }

}
