package com.example.L15MinorProject2Redis.redis;

import com.example.L15MinorProject2Redis.model.Event;
import com.example.L15MinorProject2Redis.model.EventDTO;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository  extends CrudRepository<EventDTO, String> {
}
