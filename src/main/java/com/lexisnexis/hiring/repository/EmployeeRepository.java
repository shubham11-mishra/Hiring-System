package com.lexisnexis.hiring.repository;

import com.lexisnexis.hiring.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
