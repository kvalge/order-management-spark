package org.example.controller;

import spark.Request;
import spark.Response;

import java.util.Map;

public class OrderController extends Controller {

    private final ProductController productController = new ProductController();

    public Object getProductsList(@SuppressWarnings("unused") Request request, @SuppressWarnings("unused") Response response) {
        Map<String, Object> model = productController.getAllAndValidate(request, response);

        return render("order/order.hbs", model);
    }
}
