package org.example.validation;

import org.example.database.DatabaseQuery;
import org.example.exceptions.DataExistsException;
import org.example.model.CustomerModel;
import spark.Request;

import java.util.Objects;

import static org.example.constants.Constants.REQUEST_COMPLETED;
import static org.example.constants.Constants.WHERE_EMAIL;

public class CustomerValidation {

    private final DatabaseQuery databaseQuery = new DatabaseQuery();

    public String emailAlreadyExists(Request request) {
        String email = request.queryParams("email");
        String condition = WHERE_EMAIL + email + "'";

        Object queryByEmail = databaseQuery.getByAttribute(CustomerModel.TABLE_NAME, condition);
        String[] objectSplit = queryByEmail.toString().split(",");

        if (!Objects.equals(email, objectSplit[3].substring(1))) {
            return REQUEST_COMPLETED;
        } else {
            String message = email + " already exists!";
            System.out.println(message);
            throw new DataExistsException(message);
        }
    }
}
