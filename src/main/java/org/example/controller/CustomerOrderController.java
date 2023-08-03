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
     * Renders order.hbs with model with all products and the order id.
     */
    public String toOrder(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        Map<String, Object> products = productController.getAllProductsAndValidate();
        model.putAll(products);

        Object id = request.session().attribute("attribute");
        model.put("attribute", id);

        return render("order/order.hbs", model);
    }
}
