package com.lexisnexis.hiring.service.impl;

import com.lexisnexis.hiring.entity.Employee;
import com.lexisnexis.hiring.entity.Role;
import com.lexisnexis.hiring.exception.EmployeeAlreadyExistException;
import com.lexisnexis.hiring.exception.NoEmployeeFoundException;
import com.lexisnexis.hiring.repository.EmployeeRepository;
import com.lexisnexis.hiring.repository.RoleRepository;
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
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Employee createEmployee(Employee employee) {
        Employee existingEmployee = findByEmployeeName(employee.getEmployeeName());
        if (existingEmployee != null) {
            throw new EmployeeAlreadyExistException("Employee Already Found With Name" + employee.getEmployeeName());
        } else {
            employee.setEmployeePassword(passwordEncoder.encode(employee.getEmployeePassword()));
            Set<Role> employeeRoles = new HashSet<>();
            for (Role role:employee.getRoles())
            {
                Role role1 = roleRepository.findByDesignation(role.getDesignation());
                employeeRoles.add(role1);
            }
            employee.setRoles(employeeRoles);
            return employeeRepository.save(employee);
        }
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        if (employeeRepository.findById(employeeId).isEmpty()) {
            throw new NoEmployeeFoundException("No Employee Found");
        } else {
            Optional<Employee> optionalJob = employeeRepository.findById(employeeId);
            return optionalJob.get();
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        if (employeeRepository.findAll().isEmpty()) {
            throw new NoEmployeeFoundException("No Employee Found");
        } else {
            return employeeRepository.findAll();
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        if (employeeRepository.findById(employee.getEmployeeId()).isEmpty()) {
            throw new NoEmployeeFoundException("No Employee Found");
        } else {
            Employee existingEmployee = employeeRepository.findById(employee.getEmployeeId()).get();
            existingEmployee.setEmployeeId(employee.getEmployeeId());
            existingEmployee.setEmployeeName(employee.getEmployeeName());
            existingEmployee.setEmployeePassword(employee.getEmployeePassword());
//            for (Role role : existingEmployee.getRoles()) {
//                roleRepository.deleteById(role.getRoleId());
//            }
//            existingEmployee.setRoles(employee.getRoles());
            Set<Role> employeeRoles = new HashSet<>();
            for (Role role:employee.getRoles())
            {
                Role role1 = roleRepository.findByDesignation(role.getDesignation());
                employeeRoles.add(role1);
            }
            existingEmployee.setRoles(employeeRoles);
            existingEmployee.setManager(employee.getManager());
            Employee updatedEmployee = employeeRepository.save(existingEmployee);
            return updatedEmployee;
        }
    }

    @Override
    public void deleteEmployee(int employeeId) {
        if (employeeRepository.findById(employeeId).isEmpty()) {
            throw new NoEmployeeFoundException("No Employee Found");
        } else {
            employeeRepository.deleteById(employeeId);
        }
    }

    @Override
    public List<Employee> getAllEmployeesByManagerId(int managerId) {
        Employee manager= employeeRepository.findById(managerId).get();
        System.out.println(manager);
        if(manager==null)
        {
            throw new NoEmployeeFoundException("No Employee Found");
        }
        if (employeeRepository.findAllByManager(manager).isEmpty()) {
            throw new NoEmployeeFoundException("No Employee Found");
        } else {
            List<Employee> employeeList = employeeRepository.findAllByManager(manager);
            return employeeList;
        }
    }

    @Override
    public List<Employee> getAllEmployeesByDesignation(String designation) {
        Role role= roleRepository.findByDesignation(designation);
        if(role==null)
        {
            throw new NoEmployeeFoundException("No Employee Found");
        }
        if (employeeRepository.findAllByRoles(role).isEmpty()) {
            throw new NoEmployeeFoundException("No Employee Found");
        } else {
            List<Employee> employeeList = employeeRepository.findAllByRoles(role);
            return employeeList;
        }
    }

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
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
