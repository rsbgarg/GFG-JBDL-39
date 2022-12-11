package com.example.L16KafkaDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class TestController {


    @Autowired
    KafkaTemplate<String,Object> kafkaTemplate;

    private static Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/sendData")
    ResponseEntity<String> sendData(@RequestParam String data) throws ExecutionException, InterruptedException {
       String topic = "test-topic";

       Product product = new Product();
       product.setName("Mouse");
       product.setPrice(1000.0);

       kafkaTemplate.send(topic, product.toString());

       CompletableFuture future =  kafkaTemplate.send(topic, data);

       LOGGER.info("Puhsed Data to topic {}", future.get());

       return ResponseEntity.ok("Pushed Data " + data);
    }

    @PostMapping("/sendProduct")
    ResponseEntity<String> sendProduct(@RequestParam Product data) throws ExecutionException, InterruptedException {
        String topic = "test-topic";


        KafkaProperties.Producer producer = new KafkaProperties.Producer();
        producer.getProperties();

        Product product = new Product();
        product.setName("Mouse");
        product.setPrice(1000.0);

        kafkaTemplate.send(topic, product.toString());

        CompletableFuture future =  kafkaTemplate.send(topic, data);

        LOGGER.info("Puhsed Data to topic {}", future.get());

        return ResponseEntity.ok("Pushed Data " + data);
    }



}
