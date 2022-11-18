package com.example.L6jdbcjpa;

import com.example.L6jdbcjpa.dao.PersonDao;
import com.example.L6jdbcjpa.dao.PersonNamedDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    PersonDao personDao;

    @Autowired
    PersonNamedDao personNamedDao;


    public Person addPerson(Person person){
        return personDao.createPerson(person);
    }

    public Person getPersonById(Integer id){
        return personDao.getPersonById(id);
    }

    public Person updatePerson(Integer id, Person person){
        return personDao.updatePerson(id, person);
    }


    public Person getPersonByName(String name) {
        return personNamedDao.getPersonByName(name);
    }
}
