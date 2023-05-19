package com.lexisnexis.hiring.controller;

import com.lexisnexis.hiring.entity.Candidate;
import com.lexisnexis.hiring.entity.Employee;
import com.lexisnexis.hiring.entity.JWTResponse;
import com.lexisnexis.hiring.service.CandidateService;
import com.lexisnexis.hiring.service.EmployeeService;
import com.lexisnexis.hiring.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping("/save")
    public ResponseEntity<String> saveUser(@RequestBody Employee employee) {
        Employee newEmployee=employeeService.saveEmployee(employee);
        return ResponseEntity.ok("Employee saved with id"+newEmployee);
    }
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

}
