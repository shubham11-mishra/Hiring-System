package com.lexisnexis.hiring.config;
import com.lexisnexis.hiring.entity.Employee;
import com.lexisnexis.hiring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    EmployeeService employeeService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {
        // Get the user's authorities/roles
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        Employee employee=employeeService.findByEmployeeName(authentication.getName());
        int employeeId=employee.getEmployeeId();
        // Customize the redirect URL based on the user's role
        String targetUrl;

        if (role.equals("manager")) {
            targetUrl = "/managerDashboard/"+employeeId;
        } else if (role.equals("panel")) {
            targetUrl = "/panelDashboard/"+employeeId;
        } else if(role.equals("hr")){
            // Handle other roles or scenarios as needed
            targetUrl = "/hrDashboard/"+employeeId;
        }
        else {
            targetUrl=null;
        }
        // Redirect the user to the specified URL
        response.sendRedirect(targetUrl);
    }
}
