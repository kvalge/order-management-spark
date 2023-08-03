package org.example.controller;

import org.example.exceptions.DataExistsException;
import org.example.exceptions.DataNotExistsException;
import org.example.exceptions.DataNotInsertedException;
import org.example.model.view_models.ProductViewModel;
import org.example.service.ProductService;
import org.example.validation.ProductValidation;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Long.parseLong;

public class ProductController extends Controller {

    private final ProductService productService = new ProductService();
    private final ProductValidation productValidation = new ProductValidation();

    /**
     * Checks whether the inserted product data is complete and not includes the product name already in use,
     * otherwise inserts the product data to the database. Returns respective messages.
     */
    public String insert(Request request, @SuppressWarnings("unused") Response response) {
        Map<String, Object> model = new HashMap<>();

        try {
            productValidation.dataNotInserted(request);
            productValidation.nameAlreadyExists(request);
            productService.insert(request);
            model.put("message", "Product data is saved!");
        } catch (DataNotInsertedException | DataExistsException e) {
            String message = e.getMessage();
            model.put("message", message);
            return render("product/product.hbs", model);
        }

        return render("home.hbs", model);
    }

    /**
     * Renders product list returned from getAllAndValidate method to the product page.
     */
    public String getAll(@SuppressWarnings("unused") Request request, @SuppressWarnings("unused") Response response) {
        Map<String, Object> model = getAllProductsAndValidate();

        return render("product/product.hbs", model);
    }

    /**
     * Checks whether there are products in the database to return.
     */
    public Map<String, Object> getAllProductsAndValidate() {
        List<ProductViewModel> viewModelList;
        Map<String, Object> model = new HashMap<>();

        try {
            productValidation.dataNotExists();
            viewModelList = productService.getAll();
        } catch (DataNotExistsException e) {
            String message = e.getMessage();
            model.put("message", message);
            return model;
        }
        model.put("product", viewModelList);
        return model;
    }

    public String edit(Request request, @SuppressWarnings("unused") Response response) {
        ProductViewModel viewModel = productService.getById(request);

        Map<String, Object> model = new HashMap<>();
        model.put("product", viewModel);

        return render("product/update.hbs", model);
    }

    /**
     * Checks whether the inserted product data is complete before updating. Returns respective messages.
     */
    public String update(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();

        try {
            productValidation.dataNotInserted(request);
            productService.update(request);
            model.put("message", "Product data is updated!");
        } catch (DataNotInsertedException | DataExistsException e) {
            String message = e.getMessage();
            model.put("message", message);
            return render("product/product.hbs", model);
        }

        return redirect(response, "/product");
    }

    public String delete(Request request, Response response) {
        Long id = parseLong(request.params(":id"));
        productService.delete(id);

        return redirect(response, "/home");
    }
}
