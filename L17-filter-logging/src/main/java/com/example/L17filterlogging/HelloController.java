package com.example.L17filterlogging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    private static Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    ResponseEntity<String> getHello(@RequestParam String username){
        LOGGER.info("Hello " + username);
        return ResponseEntity.ok("Hello " + username);

    }


}
