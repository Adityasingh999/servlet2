package com.wavemaker.sampleapp.employee.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wavemaker.employee.factory.EmployeeInstanceFactory;
import com.wavemaker.employee.factory.PersistenceType;
import com.wavemaker.sampleapp.employee.EmployeeManager;
import com.wavemaker.sampleapp.employee.models.Employee;
import jdk.jshell.spi.ExecutionControl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

public class EmployeeServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();
    private EmployeeManager employeeManager = EmployeeInstanceFactory.getEmployeeManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //TODO Get Persistance type from request or configuration/ property file or environment
        List<Employee> users = employeeManager.listUsers();
        response.getWriter().write(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(users));
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Employee user = objectMapper.readValue(request.getReader(), Employee.class);
        try {
            employeeManager.addUser(user);
        }
        catch (Exception e) {
            response.setStatus(500);
            response.getWriter().write("Internal server error");
        }
    }

    @Override
    protected final void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            employeeManager.deleteUser(Integer.valueOf(req.getParameter("empId")));
        }catch (Exception e) {
            resp.setStatus(500);
            resp.getWriter().write("Internal server error");
        }
    }

}
