package com.wavemaker.sampleapp.employee.database;

import com.wavemaker.sampleapp.employee.EmployeeManager;
import com.wavemaker.sampleapp.employee.models.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DatabaseEmployeeManager implements EmployeeManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseEmployeeManager.class);

    public List<Employee> listUsers() {
        List<Employee> employeeList = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnectionNonSingleTon();

        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee");
            while(resultSet.next()) {// points to Row ON each iteration
                //ID, NAME, AGE, EmployeeD
                int empId= resultSet.getInt("empid");
                String empName = resultSet.getString("empname");
                int age = resultSet.getInt("age");
                String address = resultSet.getString("address");
                employeeList.add(new Employee(empId, empName, age, address));
                LOGGER.info("Employee list is {}", employeeList);
            }
        } catch (SQLException e) {
            //TODO
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return employeeList;
    }

    public int addUser(Employee employee) {
        //TDOO
        Connection connection = DatabaseConnection.getConnectionNonSingleTon();
        try {
            String sql = "INSERT INTO  employee(empid, empname,age,address) VALUES (?, ?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, employee.getEmpId());
            statement.setString(2, employee.getEmpName());
            statement.setInt(3, employee.getAge());
            statement.setString(4, employee.getAddress());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                LOGGER.info("New employee {} added successfully", employee);
            }
        } catch (SQLException e) {
            //TODO
            e.printStackTrace();
        }
        finally {
            DatabaseConnection.closeConnection(connection);
        }
        return employee.getEmpId();
    }


    public void deleteUser(int empId) {
        Connection connection = DatabaseConnection.getConnectionNonSingleTon();
        try {
            String sql = "DELETE FROM employee WHERE empid=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, empId);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("An Employee was deleted successfully!");
            }
        } catch (SQLException e) {
            //TODO
            e.printStackTrace();
        }
        finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    @Override
    public List<Employee> searchUsers(String keyword) {
        return null;
    }

    public void updateUser(Employee employee){
        Connection connection = DatabaseConnection.getConnectionNonSingleTon();
        try {
            String sql = "UPDATE employee SET empname=?,age=?,address=? WHERE empid=?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1,employee.getEmpName());
            statement.setInt(2, employee.getAge());
            statement.setString(3, employee.getAddress());
            statement.setInt(4,employee.getEmpId() );
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing Employee was updated successfully!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DatabaseConnection.closeConnection(connection);
        }





    }


}

