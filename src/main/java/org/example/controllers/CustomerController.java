package org.example.controllers;

import org.example.services.CustomerService;
import spark.Request;
import spark.Response;

public class CustomerController {

    CustomerService customerService = new CustomerService();

    public String insert(Request request, Response response) {
        customerService.insert(request);

        return null;
    }
}
