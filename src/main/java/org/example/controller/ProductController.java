package org.example.controller;

import org.example.model.ProductViewModel;
import org.example.service.ProductService;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Long.parseLong;

public class ProductController extends Controller {

    ProductService productService = new ProductService();

    public String insert(Request request, Response response) {
        productService.insert(request);

        return redirect(response, "/home");
    }

    public String getAll(@SuppressWarnings("unused") Request request, @SuppressWarnings("unused") Response response) {
        List<ProductViewModel> viewModelList = productService.getAll();

        Map<String, Object> model = new HashMap<>();

        model.put("product", viewModelList);
        return render("product.hbs", model);
    }

    public String edit(Request request, Response response) {
        ProductViewModel viewModel = productService.getById(request);

        Map<String, Object> model = new HashMap<>();

        model.put("product", viewModel);

        return render("update.hbs", model);
    }

    public String update(Request request, Response response) {
        productService.update(request);

        return redirect(response, "/product");
    }

    public String delete(Request request, Response response) {
        Long id = parseLong(request.params(":id"));

        productService.delete(id);
        return redirect(response, "/home");
    }
}
