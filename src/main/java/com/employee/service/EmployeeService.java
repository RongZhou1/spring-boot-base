package com.employee.service;

import com.employee.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private Map<Integer, Employee> employeesMap = new HashMap<>();

    public EmployeeService() {
        Employee employee = new Employee();
        employee.setName("binfang");
        employee.setAge(18);
        employee.setId(1);

        employeesMap.put(employee.getId(), employee);
    }


    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeesMap.values());
    }

    public Employee addEmployee(Employee employee) {
        employee.setId(getNextId());
        employeesMap.put(employee.getId(), employee);
        return employee;
    }

    private int getNextId() {
        Optional<Integer> max = employeesMap.keySet().stream().max(Integer::compare);
        return (max.isPresent() ? max.get() + 1 : 1);
    }

    public void updateEmployee(Employee employee) {
        employeesMap.put(employee.getId(), employee);
    }

    public boolean isEmployeeExist(int id) {
        return employeesMap.containsKey(id);
    }

    public void deleteEmployee(int id) {
        employeesMap.remove(id);
    }

    public Employee getEmployeeById(int id) {
        return employeesMap.get(id);
    }
}
