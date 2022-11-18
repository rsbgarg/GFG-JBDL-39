package com.example.L8jdbcjpa.repo;

import com.example.L8jdbcjpa.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepo extends JpaRepository<Branch,Integer> {
}
