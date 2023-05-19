package com.lexisnexis.hiring.repository;

import com.lexisnexis.hiring.entity.Employee;
import com.lexisnexis.hiring.entity.Role;
import org.apache.catalina.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Optional<Employee> findByEmployeeName(String employeeName);

    List<Employee> findAllByManager(Employee manager);

    List<Employee> findAllByRoles(Role role);
}
