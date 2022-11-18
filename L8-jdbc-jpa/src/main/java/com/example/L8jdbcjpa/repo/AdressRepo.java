package com.example.L8jdbcjpa.repo;

import com.example.L8jdbcjpa.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdressRepo  extends JpaRepository<Address,Integer> {
}
