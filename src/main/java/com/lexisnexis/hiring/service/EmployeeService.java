package com.lexisnexis.hiring.service;

import com.lexisnexis.hiring.entity.Employee;
import org.springframework.stereotype.Service;


public interface EmployeeService {
    Employee saveEmployee(Employee employee);
}
