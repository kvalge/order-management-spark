package org.example.validation;

import org.example.database.DatabaseQuery;
import org.example.exceptions.DataExistsException;
import org.example.exceptions.DataNotInsertedException;
import org.example.model.CustomerModel;
import spark.Request;

import java.util.List;

import static org.example.constants.Constants.REQUEST_COMPLETED;
import static org.example.constants.Constants.WHERE_EMAIL;

public class CustomerValidation {

    private final DatabaseQuery databaseQuery = new DatabaseQuery();

    /**
     * Validates whether user input data is complete.
     */
    public String dataNotInserted(Request request) {
        String name = request.queryParams("full-name");
        String email = request.queryParams("email");
        String telephone = request.queryParams("telephone");

        if (name.isEmpty() || email.isEmpty() || telephone.isEmpty()) {
            String message = "Please insert all requested data!";
            throw new DataNotInsertedException(message);
        } else {
            return REQUEST_COMPLETED;
        }
    }

    /**
     * Validates whether email inserted by user is taken or free.
     */
    public String emailAlreadyExists(Request request) {
        String email = request.queryParams("email");
        String condition = WHERE_EMAIL + email + "'";

        List<Object> queryByEmail = databaseQuery.getByAttribute(CustomerModel.TABLE_NAME, condition);

        if (queryByEmail.isEmpty()) {
            return REQUEST_COMPLETED;
        } else {
            String message = email + " already exists!";
            throw new DataExistsException(message);
        }
    }
}
