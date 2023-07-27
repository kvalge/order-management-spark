package org.example.controller;

import org.example.exceptions.DataExistsException;
import org.example.exceptions.DataNotInsertedException;
import org.example.model.ProductViewModel;
import org.example.service.CustomerService;
import org.example.service.ProductService;
import org.example.validation.CustomerValidation;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerController extends Controller {

    private final CustomerService customerService = new CustomerService();
    private final CustomerValidation customerValidation = new CustomerValidation();
    private final ProductService productService = new ProductService();

    /**
     * Checks whether the user data input is complete and not includes email already in use, otherwise
     * inserts user's data to the database. Returns respective messages.
     */
    public String insert(Request request, @SuppressWarnings("unused") Response response) {
        Map<String, Object> model = new HashMap<>();

        try {
            customerValidation.dataNotInserted(request);
            customerValidation.emailAlreadyExists(request);
            customerService.insert(request);
            List<ProductViewModel> viewModelList = productService.getAll();
            model.put("product", viewModelList);
            model.put("message", "Customer data is saved!");
        } catch (DataNotInsertedException | DataExistsException e) {
            String message = e.getMessage();
            model.put("message", message);
            return render("customer/customer.hbs", model);
        }
        return render("order/order.hbs", model);
    }
}
