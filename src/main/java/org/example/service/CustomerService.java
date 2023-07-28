package org.example.service;

import org.example.mapper.CustomerMapper;
import org.example.model.entity_models.CustomerModel;
import org.example.model.view_models.CustomerViewModel;
import org.example.repository.CustomerRepository;
import spark.Request;

public class CustomerService {

    private final CustomerMapper customerMapper = new CustomerMapper();
    private final CustomerRepository customerRepository = new CustomerRepository();

    /**
     * Converts Request type to CustomerModel type in Mapper class for returning it to Repository class.
     */
    public void insert(Request request) {
        CustomerModel customer = customerMapper.requestToEntity(request);
        customerRepository.insert(customer);
    }

    public CustomerViewModel getByEmail(String email) {
        Object byEmail = customerRepository.getByEmail(email);

        return customerMapper.entityToCustomerViewModel(byEmail);
    }
}
