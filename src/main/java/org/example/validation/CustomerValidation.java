package org.example.validation;

import org.example.database.DatabaseQuery;
import org.example.exceptions.DataExistsException;
import org.example.model.CustomerModel;
import spark.Request;

import java.util.List;

import static org.example.constants.Constants.REQUEST_COMPLETED;
import static org.example.constants.Constants.WHERE_EMAIL;

public class CustomerValidation {

    private final DatabaseQuery databaseQuery = new DatabaseQuery();

    public String emailAlreadyExists(Request request) {
        String email = request.queryParams("email");
        String condition = WHERE_EMAIL + email + "'";

        List<Object> queryByEmail = databaseQuery.getByAttribute(CustomerModel.TABLE_NAME, condition);

        if (queryByEmail.isEmpty()) {
            return REQUEST_COMPLETED;
        } else {
            String message = email + " already exists!";
            System.out.println(message);
            throw new DataExistsException(message);
        }
    }
}
