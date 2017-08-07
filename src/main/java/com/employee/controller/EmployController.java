package com.employee.controller;

import com.employee.dto.EmployeeDto;
import com.employee.dto.EmployeeResource;
import com.employee.exception.ResourceNotExitException;
import com.employee.model.Employee;
import com.employee.service.EmployeeService;
import com.employee.translator.EmployeeTranslator;
import com.employee.validator.EmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static com.employee.Utils.LocationHeaderBuilder.buildLocationForHeader;
import static com.employee.translator.EmployeeTranslator.translateEmployeeDtoToModel;
import static com.employee.translator.EmployeeTranslator.translateEmployeeModelToDto;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/employees")
public class EmployController {

    @Autowired
    private EmployeeService service;

    @Autowired
    private EmployeeValidator validator;

    @RequestMapping(method = RequestMethod.GET)
    public List<EmployeeResource> getAllEmployees() {
        List<Employee> allEmployees = service.getAllEmployees();

        return allEmployees.stream()
                .map(EmployeeTranslator::translateEmployeeModelToDto)
                .map(this::buildEmployeeResourceWithHateoas).collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addEmploy(@RequestBody EmployeeDto employeeDto) {
        Employee employee = EmployeeTranslator.translateEmployeeDtoToModel(employeeDto);

        Employee addedEmployee = service.addEmployee(employee);

        URI location = buildLocationForHeader(addedEmployee.getId());

        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateEmploy(@PathVariable("id") int id,
                                       @RequestBody EmployeeDto employeeDto) throws ResourceNotExitException {
        validator.validateEmployeeExist(id);

//        if the id in body is different from the id in path, will ignore the id in body
        employeeDto.setId(id);

        service.updateEmployee(translateEmployeeDtoToModel(employeeDto));
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteEmployee(@PathVariable("id") int id) throws ResourceNotExitException {
        validator.validateEmployeeExist(id);

        service.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public EmployeeResource getEmployee(@PathVariable("id") int id) throws ResourceNotExitException {
        validator.validateEmployeeExist(id);

        EmployeeDto employeeDto = translateEmployeeModelToDto(service.getEmployeeById(id));

        return buildEmployeeResourceWithHateoas(employeeDto);
    }

    private EmployeeResource buildEmployeeResourceWithHateoas(EmployeeDto employeeDto) {
        EmployeeResource employeeResource = new EmployeeResource(employeeDto);
        employeeResource.add(linkTo(methodOn(EmployController.class).getEmployee(employeeDto.getId())).withSelfRel());
        employeeResource.add(linkTo(methodOn(AddressController.class).getAddresses(employeeDto.getId())).withRel("addresses"));
        return employeeResource;
    }


}