package com.wavemaker.employee.factory;

import com.wavemaker.sampleapp.employee.database.DatabaseEmployeeManager;
import com.wavemaker.sampleapp.employee.inmemory.InMemoryEmployeeManager;
import com.wavemaker.sampleapp.employee.EmployeeManager;

import java.util.HashMap;
import java.util.Map;

public class EmployeeInstanceFactory {

    private static Map<PersistenceType, EmployeeManager> instanceMap = new HashMap<>();

    private static final PersistenceType persistenceType = PersistenceType.valueOf(System.getProperty("persistenceImplementation", PersistenceType.DB.name()));

    public static EmployeeManager getEmployeeManager() {
        EmployeeManager employeeManager = instanceMap.get(persistenceType);
        if(employeeManager == null) {
            synchronized (EmployeeInstanceFactory.class) {
                employeeManager = instanceMap.get(persistenceType);
                if(employeeManager == null) {
                    switch (persistenceType) {
                        case IN_MEMORY:
                            employeeManager = new InMemoryEmployeeManager();
                            instanceMap.put(persistenceType, new InMemoryEmployeeManager());
                            break;
                        case DB:
                            employeeManager = new DatabaseEmployeeManager();
                            instanceMap.put(persistenceType, new DatabaseEmployeeManager());
                            break;
                    }
                }
            }
        }
        return employeeManager;
    }
}
