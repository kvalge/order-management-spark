package org.example.controller;

import org.example.model.entity_models.CustomerOrderModel;
import org.example.service.CustomerOrderService;

import java.util.Date;
import java.util.UUID;

public class CustomerOrderController extends Controller {

    private final CustomerOrderService customerOrderService = new CustomerOrderService();

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
}
