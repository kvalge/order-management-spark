package org.example.repositories;

import org.example.models.CustomerModel;

public class CustomerRepository {

    public void save(CustomerModel customerModel) {
        System.out.println(customerModel.getId());
        System.out.println(customerModel.getFullName());
        System.out.println(customerModel.getRegistrationCode());
        System.out.println(customerModel.getEmail());
        System.out.println(customerModel.getTelephone());
    }
}
