package com.example.L8jdbcjpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "salary", nullable = false)
    private Double Salary;

    @Column(name = "managername", nullable = false)
    private String managerName;


    @OneToOne(cascade = CascadeType.ALL)
    private Address address;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("employees")
    private Branch branch;

}
