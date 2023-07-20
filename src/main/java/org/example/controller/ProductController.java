package org.example.controller;

import org.example.service.ProductService;
import spark.Request;
import spark.Response;

public class ProductController {

    ProductService productService = new ProductService();

    public String insert(Request request, Response response) {
        productService.insert(request);

        return redirect(response, "/home");
    }

    public String redirect(Response response, String path) {
        response.redirect(path);
        return null;
    }
}
