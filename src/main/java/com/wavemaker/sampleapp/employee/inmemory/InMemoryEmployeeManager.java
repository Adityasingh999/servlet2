package com.wavemaker.sampleapp.employee.inmemory;

import com.wavemaker.sampleapp.employee.EmployeeManager;
import com.wavemaker.sampleapp.employee.models.Employee;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
public class InMemoryEmployeeManager implements EmployeeManager {
    private List<Employee> usersList = new ArrayList<>();

    @Override
    public int addUser(Employee user1) {
        usersList.add(user1);
        return user1.getEmpId();
    }
    @Override
    public void updateUser(Employee user) {
        ListIterator<Employee> userListIterator = usersList.listIterator();
        while (userListIterator.hasNext()) {
            Employee existingUser = userListIterator.next();
            if (existingUser.getEmpId() == user.getEmpId()) {
                //userListIterator.set(user);
                existingUser.setEmpName(user.getEmpName());
            }
        }
    }
    @Override
    public List<Employee> listUsers() {
        System.out.println(usersList);
        return usersList;
    }

    @Override
    public void deleteUser(int id) {
        boolean flag=false;

        Iterator<Employee> userIterator = usersList.iterator();
        while (userIterator.hasNext()) {

            Employee eId = userIterator.next();
            System.out.println(eId.getEmpId());
            System.out.println();
            if (eId.getEmpId() == id) {
                flag = true;
                userIterator.remove();
                break;

            }
        }
        if (flag == true) {
            System.out.println(usersList);

        } else {
            System.out.println("NOT FOUND");
        }
    }

    @Override
    public List<Employee> searchUsers(String searchWord) {
        ArrayList<Employee> searchlist = new ArrayList<Employee>();
        boolean flag = false;
        Iterator<Employee> userIterator = usersList.iterator();
        while (userIterator.hasNext()) {
            Employee user = userIterator.next();
            if (user.getEmpName().equals(searchWord)) {
                flag = true;
                searchlist.add(user);
                break;

            }
        }
        if (flag == true) {
            System.out.println(searchlist);
        } else {
            System.out.println("Not Found");
        }

        return searchlist;
    }
}

