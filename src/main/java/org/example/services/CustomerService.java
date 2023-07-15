package org.example.services;

import org.example.mappers.CustomerMapper;
import org.example.models.CustomerModel;
import org.example.repositories.CustomerRepository;
import spark.Request;

public class CustomerService {

    CustomerMapper customerMapper = new CustomerMapper();
    CustomerRepository customerRepository = new CustomerRepository();

    public void insert(Request request) {
        CustomerModel customerModel = customerMapper.createFromParams(request);
        customerRepository.save(customerModel);
    }
}
