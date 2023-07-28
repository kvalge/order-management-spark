package org.example.controller;

import org.example.exceptions.DataExistsException;
import org.example.exceptions.DataNotInsertedException;
import org.example.model.entity_models.CustomerOrderModel;
import org.example.model.view_models.CustomerViewModel;
import org.example.service.CustomerOrderService;
import org.example.service.CustomerService;
import org.example.validation.CustomerValidation;
import spark.Request;
import spark.Response;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CustomerController extends Controller {

    private final CustomerService customerService = new CustomerService();
    private final CustomerValidation customerValidation = new CustomerValidation();
    private final CustomerOrderService customerOrderService = new CustomerOrderService();

    /**
     * Checks whether the user data input is complete and not includes email already in use, otherwise
     * inserts user's data to the database. Returns respective messages.
     * Creates customer order with just inserted customer id and current date.
     */
    public String insert(Request request, @SuppressWarnings("unused") Response response) {
        Map<String, Object> model = new HashMap<>();

        try {
            customerValidation.dataNotInserted(request);
            customerValidation.emailAlreadyExists(request);
            customerService.insert(request);
        } catch (DataNotInsertedException | DataExistsException e) {
            String message = e.getMessage();
            model.put("message", message);
            return render("customer/customer.hbs", model);
        }

        String email = request.queryParams("email");

        CustomerViewModel viewModel = customerService.getByEmail(email);
        Long id = viewModel.getId();

        CustomerOrderModel customerOrderModel = new CustomerOrderModel();
        customerOrderModel.setCustomerId(id);
        customerOrderModel.setSubmissionDate(new Date());
        customerOrderService.insert(customerOrderModel);

        return redirectWith(response,"/order", model);
    }
}
