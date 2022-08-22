package com.wavemaker.sampleapp.employee.models;

public class Employee {
    private int empId;
    private String empName;
    private int age;
    private String address;
    public Employee(int empId, String empName, int age, String address) {
        this.empId = empId;
        this.empName = empName;
        this.age = age;
        this.address = address;
    }

    public Employee() {

    }

    public Employee(Employee user) {
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
