package com.example.L6jdbcjpa.dao;

import com.example.L6jdbcjpa.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonDao implements IPersonDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Autowired
    private RowMapper<Person> personRowMapper;

    public Person createPerson(Person person){
        int maxID = maxID()+1;
        String query = "insert into persons values (?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, maxID);
            ps.setString(2, person.getName());
            ps.setString(3,person.getEmail());
            ps.setString(4,person.getPhone());
            return ps;
        },keyHolder);

        person.setId(maxID);
        return person;
    }

    public Person getPersonById(int id) {

        List<Person> list;

//        String name;
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1,id);
//                ps.setString(1, name);
            }
        };

        list = jdbcTemplate.query("select * from persons where id = ?",preparedStatementSetter,personRowMapper);

        return list.get(0);

    }
    public Person getPersonByName(String name) {

        List<Person> list;
        PreparedStatementSetter preparedStatementSetter = new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1,name);
            }
        };
        list = jdbcTemplate.query("select * from persons where name = ?",preparedStatementSetter,personRowMapper);
        return list.get(0);
    }



    public Person updatePerson(Integer id, Person person){
        return person;
    }


    private int maxID(){
        String maxIdQuery = "select MAX(id) from persons";
        int id = (int) jdbcTemplate.query(maxIdQuery, new RowMapper<Object>() {

            @Override
            public Integer  mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt(1);
            }
        }).get(0);

        System.out.println(id);
        return id;
    }



}
