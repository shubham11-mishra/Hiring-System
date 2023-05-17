package com.lexisnexis.hiring.repository;

import com.lexisnexis.hiring.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Optional<Employee> findByEmployeeName(String employeeName);
}
