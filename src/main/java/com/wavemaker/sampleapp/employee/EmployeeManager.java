package com.wavemaker.sampleapp.employee;
import com.wavemaker.sampleapp.employee.models.Employee;

import java.util.List;
public interface EmployeeManager {
    int addUser(Employee user1);
    void updateUser(Employee user);
    List<Employee> listUsers();
    void deleteUser(int id);
    List<Employee> searchUsers(String keyword);
}
