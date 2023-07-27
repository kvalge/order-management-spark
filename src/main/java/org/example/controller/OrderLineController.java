package org.example.controller;

import org.example.model.ProductViewModel;
import org.example.service.ProductService;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class OrderLineController extends Controller{

    private final ProductService productService = new ProductService();

    public String orderLines(Request request, Response response) {
        ProductViewModel viewModel = productService.getById(request);

        Map<String, Object> model = new HashMap<>();
        model.put("product", viewModel);

        return render("order_line/order_line.hbs", model);
    }
}
