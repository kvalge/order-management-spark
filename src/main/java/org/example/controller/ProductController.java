package org.example.controller;

import org.example.service.ProductService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class ProductController {

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


    private String redirect(Response response, String path) {
        response.redirect(path);
        return null;
    }

    private String render(String templatePath, Map<String, Object> model) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
