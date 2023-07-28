package org.example.controller;

import org.example.model.entity_models.CustomerOrderModel;
import org.example.service.CustomerOrderService;
import spark.Request;
import spark.Response;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CustomerOrderController extends Controller {

    private final CustomerOrderService customerOrderService = new CustomerOrderService();
    private final ProductController productController = new ProductController();

    /**
     * Used in CustomerController class to insert Customer Order simultaneously with creating new Customer.
     */
    public CustomerOrderModel insertCustomerOrderModel(Long customerId) {
        CustomerOrderModel customerOrderModel = new CustomerOrderModel();
        customerOrderModel.setCustomerId(customerId);
        customerOrderModel.setSubmissionDate(new Date());
        UUID uuid = UUID.randomUUID();
        customerOrderModel.setSku_code(uuid.toString());
        customerOrderService.insert(customerOrderModel);

        return customerOrderModel;
    }

    /**
     * Returns the list of products to the order page.
     */
    public Object getProductsList(@SuppressWarnings("unused") Request request, @SuppressWarnings("unused") Response response) {
        Map<String, Object> model = new HashMap<>();
        Map<String, Object> product = productController.getAllProductsAndValidate(request, response);
        model.put("product", product);

        return render("order/order.hbs", model);
    }
}
