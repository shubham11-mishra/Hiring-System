package com.lexisnexis.hiring.service.impl;

import com.lexisnexis.hiring.entity.Employee;
import com.lexisnexis.hiring.repository.EmployeeRepository;
import com.lexisnexis.hiring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public Employee saveEmployee(Employee employee) {
        employee.setEmployeePassword(passwordEncoder.encode(employee.getEmployeePassword()));
        return employeeRepository.save(employee);
    }
    public Employee findByEmployeeName(String employeeName) {
        Optional<Employee> employee = employeeRepository.findByEmployeeName(employeeName);
        if (employee.isPresent())
            return employee.get();
        return null;
    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = findByEmployeeName(username);
        if (employee == null)
            throw new UsernameNotFoundException(
                    new StringBuffer().append("Employee name ").append(username).append(" not found!").toString());

        List<GrantedAuthority> authorities = employee.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getDesignation()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(username, employee.getEmployeePassword(), authorities);
    }

}
