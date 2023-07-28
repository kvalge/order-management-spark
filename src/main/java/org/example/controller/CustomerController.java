package org.example.controller;

import org.example.exceptions.DataExistsException;
import org.example.exceptions.DataNotInsertedException;
import org.example.model.entity_models.CustomerOrderModel;
import org.example.model.view_models.CustomerOrderViewModel;
import org.example.model.view_models.CustomerViewModel;
import org.example.service.CustomerOrderService;
import org.example.service.CustomerService;
import org.example.validation.CustomerValidation;
import spark.Request;
import spark.Response;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class CustomerController extends Controller {

    private final CustomerService customerService = new CustomerService();
    private final CustomerValidation customerValidation = new CustomerValidation();
    private final CustomerOrderService customerOrderService = new CustomerOrderService();
    private final CustomerOrderController customerOrderController = new CustomerOrderController();
    private final ProductController productController = new ProductController();

    /**
     * Checks whether the user data input is complete and not includes email already in use, otherwise
     * inserts user's data to the database. Returns respective messages.
     * Creates customer order with just inserted Customer id.
     * Adds to the model the product list to render it to the order.hbs.
     */
    public String insert(Request request, @SuppressWarnings("unused") Response response) throws ParseException {
        Map<String, Object> model = new HashMap<>();
        String email = request.queryParams("email");

        try {
            customerValidation.dataNotInserted(request);
            customerValidation.emailAlreadyExists(request);
            customerService.insert(request);
        } catch (DataNotInsertedException | DataExistsException e) {
            String message = e.getMessage();
            model.put("message", message);
            return render("customer/customer.hbs", model);
        }

        CustomerViewModel viewModel = customerService.getByEmail(email);
        Long customerId = viewModel.getId();

        CustomerOrderModel customerOrderModel = customerOrderController.insertCustomerOrderModel(customerId);

        String sku_code = customerOrderModel.getSku_code();
        CustomerOrderViewModel customerOrder = customerOrderService.getBySkuCode(sku_code);

        model.put("order", customerOrder);

        Map<String, Object> products = productController.getAllProductsAndValidate();
        model.putAll(products);

        return render("order/order.hbs", model);
    }
}
