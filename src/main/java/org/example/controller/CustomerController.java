package org.example.controller;

import org.example.service.CustomerService;
import org.example.validation.CustomerValidation;
import spark.Request;
import spark.Response;

public class CustomerController extends Controller {

    private final CustomerService customerService = new CustomerService();

    private final CustomerValidation customerValidation = new CustomerValidation();

    public String insert(Request request, Response response) {
        customerValidation.emailAlreadyExists(request);

        customerService.insert(request);

        return redirect(response, "/home");
    }
}
