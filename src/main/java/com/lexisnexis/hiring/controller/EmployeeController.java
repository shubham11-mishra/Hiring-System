package com.lexisnexis.hiring.controller;

import com.lexisnexis.hiring.dto.Login;
import com.lexisnexis.hiring.entity.Employee;
import com.lexisnexis.hiring.entity.Role;
import com.lexisnexis.hiring.repository.RoleRepository;
import com.lexisnexis.hiring.service.EmployeeService;
import com.lexisnexis.hiring.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public  String getlogin()
    {
        return  "redirect:/login";
    }

    @RequestMapping(value = "employee/createRole")
    public String getRolePage()
    {
        return "createRole";
    }

    @RequestMapping(value = "employee/save")
    public String getEmployeePage(Model model)
    {
        List<Role> roles=roleRepository.findAll();
        List<Employee> managerList = employeeService.getAllEmployeesByDesignation("manager");
        model.addAttribute("listOfRoles",roles);
        model.addAttribute("listOfManagers",managerList);
        return "createEmployee";
    }

    @RequestMapping(value ="/login", method = RequestMethod.GET)
    public String getLoginPage(Model model) {
        model.addAttribute("login", new Login());
        return "login";
    }
    @RequestMapping(value ="/managerDashboard/{id}", method = RequestMethod.GET)
    public String getManagerDashboard(Model model,@PathVariable Integer id ) {
        model.addAttribute("manager",employeeService.getEmployeeById(id));
        return "managerDashboard";
    }
    @RequestMapping(value ="/panelDashboard/{id}", method = RequestMethod.GET)
    public String getPanelDashboard(Model model,@PathVariable Integer id ) {
        model.addAttribute("manager",employeeService.getEmployeeById(id));
        return "panelDashboard";
    }
    @RequestMapping(value ="/hrDashboard/{id}", method = RequestMethod.GET)
    public String getHRDashboard(Model model,@PathVariable Integer id ) {
        model.addAttribute("manager",employeeService.getEmployeeById(id));
        return "hrDashboard";
    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ResponseEntity<JWTResponse> loginEmployee(@RequestBody Login login)
//    {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        login.getUsername(),
//                        login.getPassword())
//        );
//        String token=jwtUtil.generateToken(login.getUsername());
//        return ResponseEntity.ok(new JWTResponse(token,"JWT Token Generated"));
//    }
    @RequestMapping(value = "/employee/welcome", method = RequestMethod.GET)
    public ResponseEntity<String> accessEmployeeData(Principal p) {
        return ResponseEntity.ok("Hello Employee:"+p.getName());
    }
    @PostMapping("employee/save")
    public String createEmployee(@ModelAttribute Employee employee)  {
        Employee savedEmployee = employeeService.createEmployee(employee);
        return "redirect:/";
    }
    @PostMapping("employee/createrole")
    public String createRole(@ModelAttribute Role role)  {
        Role newRole = employeeService.createRole(role);
        return "redirect:/";
    }
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int employeeId){
        Employee employee = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    @RequestMapping(value = "/employee/get-all" ,method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employeeList = employeeService.getAllEmployees();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllEmployeesByDesignation/{designation}" ,method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getAllEmployeesByDesignation(@PathVariable("designation") String designation){
        List<Employee> employeeList = employeeService.getAllEmployeesByDesignation(designation);
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }
    @RequestMapping(value = "/get-all/{manager-id}" ,method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getAllEmployeesByManagerId(@PathVariable("manager-id") int managerId){
        List<Employee> employeeList = employeeService.getAllEmployeesByManagerId(managerId);
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @PutMapping("employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") int employeeId,
                                                @RequestBody Employee employee){
        employee.setEmployeeId(employeeId);
        Employee updatedEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int employeeId){
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>("Employee successfully deleted!", HttpStatus.OK);
    }

}
