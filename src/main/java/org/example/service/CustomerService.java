package org.example.service;

import org.example.mapper.CustomerMapper;
import org.example.model.CustomerModel;
import org.example.repository.CustomerRepository;
import spark.Request;

public class CustomerService {

    private final CustomerMapper customerMapper = new CustomerMapper();
    private final CustomerRepository customerRepository = new CustomerRepository();

    public void insert(Request request) {
        CustomerModel customer = customerMapper.requestToEntity(request);
        customerRepository.insert(customer);
    }
}
