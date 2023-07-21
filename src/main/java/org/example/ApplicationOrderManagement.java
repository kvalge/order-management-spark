package org.example;

import org.example.controller.ProductController;
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
        ProductController productController = new ProductController();

        get("/home", (request, response) -> {
            return new ModelAndView(null, "home.hbs");
        }, new HandlebarsTemplateEngine());

        get("/customer", (request, response) -> {
            return new ModelAndView(null, "customer.hbs");
        }, new HandlebarsTemplateEngine());

        get("/product", productController::getAll);

        post("/customer", customerController::insert);

        post("/product", productController::insert);
        post("/product/:id/delete", productController::delete);
    }
}