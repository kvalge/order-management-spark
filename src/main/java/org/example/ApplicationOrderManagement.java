package org.example;

import org.example.controllers.CustomerController;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.*;

public class ApplicationOrderManagement {

    public static void main(String[] args) {
        port(8080);

        CustomerController customerController = new CustomerController();

        get("/customer", (request, response) -> {
            return new ModelAndView(null, "customer.hbs");
        }, new HandlebarsTemplateEngine());

        post("/customer", customerController::insert);
    }
}