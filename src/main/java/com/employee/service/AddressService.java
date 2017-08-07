package com.employee.service;

import com.employee.model.Address;
import com.employee.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AddressService {
    private Map<Integer, Address> addressMap = new HashMap<>();

    public Address addAddress(Address address) {
        address.setId(getNextId());
        addressMap.put(address.getId(), address);
        return address;
    }

    private int getNextId() {
        Optional<Integer> max = addressMap.keySet().stream().max(Integer::compare);
        return (max.isPresent() ? max.get() + 1 : 1);
    }

    public List<Address> getAllAddresses() {
        return new ArrayList<>(addressMap.values());
    }

    public boolean isAddressExisted(int id) {
        return addressMap.containsKey(id);
    }

    public Address getAddressById(int id) {
        return addressMap.get(id);
    }
}
