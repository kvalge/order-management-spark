package org.example.controller;

import org.example.service.ProductService;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ProductController extends Controller{

    ProductService productService = new ProductService();

    public String insert(Request request, Response response) {
        productService.insert(request);

        return redirect(response, "/home");
    }

    public String getAll(@SuppressWarnings("unused") Request request, @SuppressWarnings("unused") Response response) {
        Object[] products = productService.getAll().toArray();

        Map<String, Object> model = new HashMap<>();

        model.put("product", products);
        return render("product.hbs", model);
    }
}