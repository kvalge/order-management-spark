package org.example.repository;

import org.example.database.DatabaseQuery;
import org.example.model.CustomerModel;

import java.util.LinkedHashMap;
import java.util.Map;

public class CustomerRepository {

    DatabaseQuery databaseQuery = new DatabaseQuery();

    public void insert(CustomerModel customerModel) {
        databaseQuery.insert(CustomerModel.TABLE_NAME, createCustomerMap(customerModel));
    }

    private Map<String, Object> createCustomerMap(CustomerModel customer) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();

        map.put("full_name", customer.getFullName());
        map.put("registration_code", customer.getRegistrationCode());
        map.put("email", customer.getEmail());
        map.put("telephone", customer.getTelephone());

        return map;
    }
}
