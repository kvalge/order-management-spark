package org.example;

import org.example.controller.*;
import org.example.database.DatabaseMigrator;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.*;

public class ApplicationOrderManagement {

    public static void main(String[] args) {
        port(8080);

        DatabaseMigrator databaseMigrator = new DatabaseMigrator();
        databaseMigrator.migrate();

        HomeController homeController = new HomeController();
        CustomerController customerController = new CustomerController();
        ProductController productController = new ProductController();
        CustomerOrderController customerOrderController = new CustomerOrderController();
        OrderLineController orderLineController = new OrderLineController();

        get("/customer", (request, response) ->
                new ModelAndView(null, "customer/customer.hbs"), new HandlebarsTemplateEngine());

        get("/cart", (request, response) ->
                new ModelAndView(null, "order_line/cart.hbs"), new HandlebarsTemplateEngine());

        get("/home", homeController::index);
        get("/product", productController::getAll);
        get("/product/:id", productController::edit);
        get("/order", customerOrderController::toOrder);
        post("/customer", customerController::insert);
        post("/product", productController::insert);
        post("/product/:id/update", productController::update);
        post("/product/:id/delete", productController::delete);
        post("/product/:id/order/:attribute", orderLineController::insert);
        post("/order/:id", orderLineController::insert);
    }
}