package com.example.L6jdbcjpa.dao;

import com.example.L6jdbcjpa.Person;

public interface IPersonDao {


    public Person createPerson(Person person);

    public Person getPersonById(int id);

    public Person getPersonByName(String name);
}
