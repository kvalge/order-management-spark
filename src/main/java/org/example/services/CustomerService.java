package org.example.services;

import org.example.mappers.CustomerMapper;
import org.example.models.CustomerModel;
import org.example.repositories.CustomerRepository;
import spark.Request;

public class CustomerService {

    CustomerMapper customerMapper = new CustomerMapper();
    CustomerRepository customerRepository = new CustomerRepository();

    public void insert(Request request) {
        CustomerModel customer = customerMapper.requestToEntity(request);
        customerRepository.save(customer);
    }
}
