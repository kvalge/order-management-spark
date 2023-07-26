package org.example.mapper;

import org.example.model.CustomerModel;
import spark.Request;

import java.util.UUID;

public class CustomerMapper {

    /**
     * Converts the Request type (returned from the template form) to the CustomerModel attributes.
     */
    public CustomerModel requestToEntity(Request request) {
        CustomerModel customer = new CustomerModel();

        customer.setFullName(request.queryParams("full-name"));
        UUID uuid = UUID.randomUUID();
        customer.setRegistrationCode(uuid.toString());
        customer.setEmail(request.queryParams("email"));
        customer.setTelephone(request.queryParams("telephone"));

        return customer;
    }
}
