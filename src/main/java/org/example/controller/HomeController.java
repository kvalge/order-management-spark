package org.example.controller;

import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class HomeController extends Controller{

    private final ProductController productController = new ProductController();

    public String index(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        Map<String, Object> products = productController.getAllProductsAndValidate();
        model.putAll(products);

        return render("home.hbs", model);
    }
}
