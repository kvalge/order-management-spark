package org.example.controller;

import org.example.service.CustomerService;
import spark.Request;
import spark.Response;

public class CustomerController {

    CustomerService customerService = new CustomerService();

    public String insert(Request request, Response response) {
        customerService.insert(request);

        return redirect(response, "/home");
    }

    public String redirect(Response response, String path) {
        response.redirect(path);
        return null;
    }
}