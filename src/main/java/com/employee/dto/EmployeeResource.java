package com.employee.dto;

import com.employee.controller.AddressController;
import com.employee.controller.EmployController;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class EmployeeResource extends ResourceSupport {
    private final EmployeeDto content;

    public EmployeeResource(EmployeeDto content) {
        this.content = content;
}

    public EmployeeDto getContent() {
        return content;
    }
}
