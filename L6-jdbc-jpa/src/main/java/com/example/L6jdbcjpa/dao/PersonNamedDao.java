package com.example.L6jdbcjpa.dao;

import com.example.L6jdbcjpa.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
@Repository
public class PersonNamedDao implements IPersonDao{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private RowMapper<Person> personRowMapper;

    @Override
    public Person createPerson(Person person) {
        return null;
    }

    @Override
    public Person getPersonById(int id) {
        return null;
    }

    @Override
    public Person getPersonByName(String name) {
        List<Person> list;
        String query = "select * from persons where name = :name";
        MapSqlParameterSource mapSqlParameterSource =  new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name", name);

        list = jdbcTemplate.query(query, mapSqlParameterSource, personRowMapper);
        return list.get(0);
    }
}
