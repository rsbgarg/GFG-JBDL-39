package com.example.L8jdbcjpa.service;

import com.example.L8jdbcjpa.entity.Branch;
import com.example.L8jdbcjpa.entity.Employee;
import com.example.L8jdbcjpa.repo.AdressRepo;
import com.example.L8jdbcjpa.repo.BranchRepo;
import com.example.L8jdbcjpa.repo.EmployeeRepo;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private AdressRepo adressRepo;

    @Autowired
    private BranchRepo branchRepo;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Employee createEmployee(Employee employee) throws RuntimeException {


        Branch branch = new Branch();
        branch.setName(employee.getBranch().getName());
        branch = branchRepo.save(branch);
        employee.setBranch(branch);
        //Stuff happening
        employeeRepo.save(employee);

        if(employee.getName().equals("ramesh")){
            throw new RuntimeException();
        }


        return employee;
    }
}
