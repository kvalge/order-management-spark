package org.example.mapper;

import org.example.model.entity_models.CustomerModel;
import org.example.model.view_models.CustomerViewModel;
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

    /**
     * Converts the Object type (returned from database) to
     * the CustomerViewModel.
     */
    public CustomerViewModel entityToCustomerViewModel(Object object) {
        String[] objectSplit = object.toString().split(",");

        CustomerViewModel customerViewModel = new CustomerViewModel();
        customerViewModel.setId(Long.valueOf(objectSplit[0].substring(1)));
        customerViewModel.setFullName(objectSplit[1]);
        customerViewModel.setRegistrationCode(objectSplit[2]);
        customerViewModel.setEmail(objectSplit[3]);
        customerViewModel.setTelephone(objectSplit[4]);

        return customerViewModel;
    }
}
