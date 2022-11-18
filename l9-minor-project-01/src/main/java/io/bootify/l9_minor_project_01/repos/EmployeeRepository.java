package io.bootify.l9_minor_project_01.repos;

import io.bootify.l9_minor_project_01.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
