package com.employee.validator;

import com.employee.exception.ResourceNotExitException;
import com.employee.service.AddressService;
import com.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeValidator {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    AddressService addressService;

    public void validateEmployeeExist(int id){
        if(!employeeService.isEmployeeExist(id)){
            throw  new ResourceNotExitException("Employee with id=%s does not exist");
        }
    }

    public void validateAddressExist(int id) {
        if(!addressService.isAddressExisted(id)){
            throw  new ResourceNotExitException("Address with id=%s does not exist");
        }


    }
}
