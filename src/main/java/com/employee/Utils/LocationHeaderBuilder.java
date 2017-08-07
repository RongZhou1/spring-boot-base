package com.employee.Utils;

import com.employee.model.Employee;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class LocationHeaderBuilder {

    public static URI buildLocationForHeader(int id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }
}
