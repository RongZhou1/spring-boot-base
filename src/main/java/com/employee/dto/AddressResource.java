package com.employee.dto;

import org.springframework.hateoas.ResourceSupport;

public class AddressResource extends ResourceSupport {
    private final AddressDto content;

    public AddressResource(AddressDto content) {
        this.content = content;
}

    public AddressDto getContent() {
        return content;
    }
}
