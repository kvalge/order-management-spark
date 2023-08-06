package org.example.controller;

import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class HomeController extends Controller{

    private final ProductController productController = new ProductController();

    public String index(@SuppressWarnings("unused") Request request, @SuppressWarnings("unused") Response response) {
        Map<String, Object> products = productController.getAllProductsAndValidate();
        Map<String, Object> model = new HashMap<>(products);

        return render("home.hbs", model);
    }
}
