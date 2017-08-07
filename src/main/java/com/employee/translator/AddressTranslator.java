package com.employee.translator;

import com.employee.dto.AddressDto;
import com.employee.model.Address;

public class AddressTranslator {
    public static AddressDto translateAddressModelToDto(Address address){
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setLine(address.getLine());
        addressDto.setCity(address.getCity());
        addressDto.setType(address.getType());
        return addressDto;
    }

    public static Address translateAddressDtoToModel(AddressDto addressDto){
        Address address = new Address();
        address.setCity(addressDto.getCity());
        address.setId(addressDto.getId());
        address.setLine(addressDto.getLine());
        address.setType(addressDto.getType());
        return address;
    }

}
