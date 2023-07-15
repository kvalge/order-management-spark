package org.example;

import spark.ModelAndView;

import static spark.Spark.*;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Main {
    public static void main(String[] args) {
        port(8080);

        get("/customer", (request, response) -> {
            return new ModelAndView(null, "customer.hbs");
        }, new HandlebarsTemplateEngine());
    }
}