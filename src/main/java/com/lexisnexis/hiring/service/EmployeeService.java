package com.lexisnexis.hiring.service;

import com.lexisnexis.hiring.entity.Employee;
import com.lexisnexis.hiring.entity.Role;

import java.util.List;


public interface EmployeeService {
     Employee createEmployee(Employee employee) ;

    Employee getEmployeeById(int employeeId);

    List<Employee> getAllEmployees();

    Employee updateEmployee(Employee employee);

    void deleteEmployee(int employeeId);

    List<Employee> getAllEmployeesByManagerId(int managerId);

    List<Employee> getAllEmployeesByDesignation(String designation);

    Role createRole(Role role);
}
