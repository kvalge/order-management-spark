package org.example.repository;

import org.example.database.DatabaseQuery;
import org.example.model.entity_models.CustomerModel;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.example.constants.Constants.WHERE_EMAIL;

public class CustomerRepository {

    private final DatabaseQuery databaseQuery = new DatabaseQuery();

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

    public Object getByEmail(String email) {
        String condition = WHERE_EMAIL + email + "'";

        return databaseQuery.getByAttribute(CustomerModel.TABLE_NAME, condition);
    }
}
