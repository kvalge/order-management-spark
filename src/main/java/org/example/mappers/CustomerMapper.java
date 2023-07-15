package org.example.mappers;

import org.example.models.CustomerModel;
import spark.Request;

import java.util.UUID;

public class CustomerMapper {

    public CustomerModel requestToEntity(Request request) {
        CustomerModel customer = new CustomerModel();

        customer.setId(1L);
        customer.setFullName(request.queryParams("full-name"));
        UUID uuid = UUID.randomUUID();
        customer.setRegistrationCode(uuid.toString());
        customer.setEmail(request.queryParams("email"));
        customer.setTelephone(request.queryParams("telephone"));
        return customer;
    }
}
