package com.employee.translator;

import com.employee.dto.EmployeeDto;
import com.employee.model.Employee;

public class EmployeeTranslator {

    public static EmployeeDto translateEmployeeModelToDto(Employee employee){
        EmployeeDto dto = new EmployeeDto();
        dto.setName(employee.getName());
        dto.setId(employee.getId());
        dto.setAge(employee.getAge());
        return dto;
    }

    public static Employee translateEmployeeDtoToModel(EmployeeDto employeeDto){
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setAge(employeeDto.getAge());
        employee.setId(employeeDto.getId());
        return employee;
    }
}
