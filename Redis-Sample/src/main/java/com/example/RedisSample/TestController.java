package com.example.RedisSample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private RedisTemplate<String, Person> personRedisTemplate;

    @Autowired
    private RedisTemplate<String, Product> productRedisTemplate;

    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/sethello")
    ResponseEntity<String> setHello(){
        redisTemplate.opsForValue().set("thread-name",Thread.currentThread().getName());
        return ResponseEntity.ok("Hello");
    }

    @GetMapping("/gethello")
    ResponseEntity<String> getHello(){
        String result = redisTemplate.opsForValue().get("thread-name");
        return ResponseEntity.ok(result);
    }

    @PostMapping("/person")
    ResponseEntity<Person> createPerson(@RequestBody Person person){
        Long id = personRedisTemplate.opsForValue().increment("person.id");
        //personRedisTemplate.expire("key",10, TimeUnit.HOURS);
        person.setId(id);

        personRedisTemplate.opsForValue().set(getPersonKey(id),person);
        return ResponseEntity.ok(person);
    }

    @PostMapping("/addInQueue/{id}")
    ResponseEntity<Person> addInQueue(@PathVariable Long id){
        Person person = personRedisTemplate.opsForValue().get(getPersonKey(id));
        personRedisTemplate.opsForList().leftPush("PersonList",person);
        return ResponseEntity.ok(person);
    }


    @GetMapping("/processList")
    ResponseEntity<Person> processList(){
        Person person = personRedisTemplate.opsForList().rightPop("PersonList");
        return ResponseEntity.ok(person);
    }


    @GetMapping("/person/{id}")
    ResponseEntity<Person> getPerson(@PathVariable Long id){
        Person person = personRedisTemplate.opsForValue().get(getPersonKey(id));
        if(person == null){
            //read from db

            //write in redis
        }
        return ResponseEntity.ok(person);
    }




    private String getPersonKey(Long id){
        String key = "person:"+id;
        return key;
    }

    private String getProductKey(Long id){
        String key = "product:"+id;
        return key;
    }



    @PostMapping("/product")
    ResponseEntity<Product> createProduct(@RequestBody Product product){
        Long id = productRedisTemplate.opsForValue().increment("product.id");
        product.setId(id);
        String key = getProductKey(id);
        productRepository.save(product);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/product/{id}")
    ResponseEntity<Product> getProduct(@PathVariable Long id){

        Product product = productRepository.findById(String.valueOf(id)).get();
        return ResponseEntity.ok(product);
    }

    @GetMapping("/product/all")
    ResponseEntity<List<Product>> getAllProducts(){

        List<Product> product = new ArrayList<>();
        productRepository.findAll().forEach(product::add);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/product/delete/{id}")
    ResponseEntity<Product> deleteProduct(@PathVariable Long id){

        Product product = productRepository.findById(String.valueOf(id)).get();
        try {
            productRepository.deleteById(String.valueOf(id));
        } catch (Exception e){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }



}
