package com.employee.controller;

import com.employee.Utils.LocationHeaderBuilder;
import com.employee.dto.AddressDto;
import com.employee.dto.AddressResource;
import com.employee.exception.ResourceNotExitException;
import com.employee.model.Address;
import com.employee.service.AddressService;
import com.employee.translator.AddressTranslator;
import com.employee.validator.EmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static com.employee.translator.AddressTranslator.translateAddressDtoToModel;
import static com.employee.translator.AddressTranslator.translateAddressModelToDto;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/employees/{employeeId}/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private EmployeeValidator validator;


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addAddress(@PathVariable("employeeId") int employeeId,
                                       @RequestBody AddressDto addressDto) throws ResourceNotExitException {
        validator.validateEmployeeExist(employeeId);

        Address address = translateAddressDtoToModel(addressDto);
        address.setEmployeeId(employeeId);

        Address addedAddress = addressService.addAddress(address);

        URI location = LocationHeaderBuilder.buildLocationForHeader(addedAddress.getId());
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<AddressResource> getAddresses(@PathVariable("employeeId") int employeeId) throws ResourceNotExitException {
        validator.validateEmployeeExist(employeeId);

        List<Address> allAddresses = addressService.getAllAddresses();

        return allAddresses.stream()
                .map(AddressTranslator::translateAddressModelToDto)
                .map(addressDto -> buildAddressResourceWithHateoas(addressDto, employeeId))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public AddressResource getAddress(@PathVariable("employeeId") int employeeId,
                                      @PathVariable("id") int id) throws ResourceNotExitException {
        validator.validateEmployeeExist(employeeId);
        validator.validateAddressExist(id);

        Address address = addressService.getAddressById(id);
        AddressDto addressDto = translateAddressModelToDto(address);

        return buildAddressResourceWithHateoas(addressDto, employeeId);
    }


    private AddressResource buildAddressResourceWithHateoas(AddressDto addressDto,
                                                            int employeeId ) {
        AddressResource addressResource = new AddressResource(addressDto);
        addressResource.add(linkTo(methodOn(AddressController.class)
                .getAddress(employeeId, addressDto.getId())).withSelfRel());
        addressResource.add(linkTo(methodOn(EmployController.class)
                .getEmployee(employeeId)).withRel("employee"));
        return addressResource;
    }


}