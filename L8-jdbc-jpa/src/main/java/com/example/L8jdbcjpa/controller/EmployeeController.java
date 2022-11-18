package com.example.L8jdbcjpa.controller;

import com.example.L8jdbcjpa.dto.EmployeeRequestDTO;
import com.example.L8jdbcjpa.dto.EmployeeResponseDTO;
import com.example.L8jdbcjpa.entity.Employee;
import com.example.L8jdbcjpa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("create")
    private ResponseEntity<EmployeeResponseDTO>
    createEmployeeWithDto(@RequestBody EmployeeRequestDTO employeeRequestDTO) {

        // Retreive request
        Employee employee = new Employee();
        employee.setName(employeeRequestDTO.getFullName());
        employee.setSalary(employeeRequestDTO.getSalary());

        try {
            employee = employeeService.createEmployee(employee);
        } catch (Exception e) {
            System.out.println("EAT");
        }


        //SET Response
        EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
        employeeResponseDTO.setEmployeeID(employee.getId());
        employeeResponseDTO.setFullName(employee.getName());
        employeeResponseDTO.setSalary(employee.getSalary());

        return ResponseEntity.ok().body(employeeResponseDTO);
    }


    @PostMapping("createemployee")
    private ResponseEntity<Employee>
    createEmployee(@RequestBody Employee employee) {

        employee = employeeService.createEmployee(employee);
        return ResponseEntity.ok().body(employee);
    }


}
