package org.example;

import org.example.controllers.CustomerController;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.*;

public class ApplicationOrderManagement {

    public static void main(String[] args) {
        CustomerController customerController = new CustomerController();
        port(8080);

        get("/customer", (request, response) -> {
            return new ModelAndView(null, "customer.hbs");
        }, new HandlebarsTemplateEngine());

        post("/customer", customerController::insert);
    }
}