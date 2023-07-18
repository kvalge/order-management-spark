package org.example;

import org.example.database.DatabaseMigrator;

import org.example.controller.CustomerController;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.*;

public class ApplicationOrderManagement {

    public static void main(String[] args) {
        port(8080);

        DatabaseMigrator databaseMigrator = new DatabaseMigrator();
        databaseMigrator.migrate();

        CustomerController customerController = new CustomerController();

        get("/customer", (request, response) -> {
            return new ModelAndView(null, "customer.hbs");
        }, new HandlebarsTemplateEngine());

        post("/customer", customerController::insert);
    }
}