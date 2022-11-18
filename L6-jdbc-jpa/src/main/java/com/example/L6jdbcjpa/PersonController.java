package com.example.L6jdbcjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
        person = personService.addPerson(person);
        return ResponseEntity.ok(person);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable("id") Integer id){
        Person person = personService.getPersonById(id);
        if(person == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(person);
    }

    @GetMapping("getByName")
    public ResponseEntity<Person> getPerson(@RequestParam String name){
        Person person = personService.getPersonByName(name);
        if(person == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(person);
    }





}
