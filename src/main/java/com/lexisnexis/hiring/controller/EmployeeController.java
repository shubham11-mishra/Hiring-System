package com.lexisnexis.hiring.controller;

import com.lexisnexis.hiring.entity.Employee;
import com.lexisnexis.hiring.entity.JWTResponse;
import com.lexisnexis.hiring.entity.Role;
import com.lexisnexis.hiring.service.EmployeeService;
import com.lexisnexis.hiring.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<JWTResponse> loginUser(@RequestBody Employee employeeRequest)
    {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        employeeRequest.getEmployeeName(),
                        employeeRequest.getEmployeePassword()
                )
        );
        String token=jwtUtil.generateToken(employeeRequest.getEmployeeName());
        return ResponseEntity.ok(new JWTResponse(token,"JWT Token Generated"));
    }
    @GetMapping("/welcome")
    public ResponseEntity<String> accessUserData(Principal p) {
        return ResponseEntity.ok("Hello user:"+p.getName());
    }
    @PostMapping("/save")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee)  {
        Employee savedEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.OK);
    }
    @PostMapping("/createrole")
    public ResponseEntity<Role> createRole(@RequestBody Role role)  {
        Role newRole = employeeService.createRole(role);
        return new ResponseEntity<>(newRole, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int employeeId){
        Employee employee = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employeeList = employeeService.getAllEmployees();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }
    @GetMapping("/getAllEmployeesByDesignation/{designation}")
    public ResponseEntity<List<Employee>> getAllEmployeesByDesignation(@PathVariable("designation") String designation){
        List<Employee> employeeList = employeeService.getAllEmployeesByDesignation(designation);
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }
    @GetMapping("/get-all/{manager-id}")
    public ResponseEntity<List<Employee>> getAllEmployeesByManagerId(@PathVariable("manager-id") int managerId){
        System.out.println("Hello");
        List<Employee> employeeList = employeeService.getAllEmployeesByManagerId(managerId);
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") int employeeId,
                                                @RequestBody Employee employee){
        employee.setEmployeeId(employeeId);
        Employee updatedEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int employeeId){
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>("Employee successfully deleted!", HttpStatus.OK);
    }
}
