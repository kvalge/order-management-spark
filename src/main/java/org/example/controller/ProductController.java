package org.example.controller;

import org.example.service.ProductService;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Long.parseLong;

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

    public String delete(Request request, Response response) {
        Long id = parseLong(request.params(":id"));

        productService.delete(id);
        return redirect(response, "/home");
    }
}
