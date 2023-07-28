package org.example.controller;

import org.example.database.DatabaseQuery;
import org.example.exceptions.DataExistsException;
import org.example.exceptions.DataNotInsertedException;
import org.example.model.entity_models.CustomerOrderModel;
import org.example.model.view_models.CustomerViewModel;
import org.example.service.CustomerOrderService;
import org.example.service.CustomerService;
import org.example.validation.CustomerValidation;
import spark.Request;
import spark.Response;

import java.util.*;

import static org.example.constants.Constants.WHERE_SKU_CODE;

public class CustomerController extends Controller {

    private final CustomerService customerService = new CustomerService();
    private final CustomerValidation customerValidation = new CustomerValidation();
    private final CustomerOrderService customerOrderService = new CustomerOrderService();
    private final DatabaseQuery databaseQuery = new DatabaseQuery();

    /**
     * Checks whether the user data input is complete and not includes email already in use, otherwise
     * inserts user's data to the database. Returns respective messages.
     * Creates customer order with just inserted customer id and current date.
     */
    public String insert(Request request, @SuppressWarnings("unused") Response response) {
        Map<String, Object> model = new HashMap<>();
        String email = request.queryParams("email");

        try {
            customerValidation.dataNotInserted(request);
            customerValidation.emailAlreadyExists(request);
            customerService.insert(request);
            model.put("email", email);
        } catch (DataNotInsertedException | DataExistsException e) {
            String message = e.getMessage();
            model.put("message", message);
            return render("customer/customer.hbs", model);
        }

        CustomerViewModel viewModel = customerService.getByEmail(email);
        Long id = viewModel.getId();

        CustomerOrderModel customerOrderModel = new CustomerOrderModel();
        customerOrderModel.setCustomerId(id);
        customerOrderModel.setSubmissionDate(new Date());
        UUID uuid = UUID.randomUUID();
        customerOrderModel.setSku_code(uuid.toString());
        customerOrderService.insert(customerOrderModel);

        String sku_code = customerOrderModel.getSku_code();
        String condition = WHERE_SKU_CODE + sku_code + "'";
        List<Object> query = databaseQuery.getByAttribute(CustomerOrderModel.TABLE_NAME, condition);

        model.put("message", "juhhei");
        model.put("order", query.toArray()[0]);
        System.out.println(query.toArray().length);

        return render("order/order.hbs", model);
    }
}
