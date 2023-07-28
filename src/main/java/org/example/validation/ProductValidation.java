package org.example.validation;

import org.example.database.DatabaseQuery;
import org.example.exceptions.DataExistsException;
import org.example.exceptions.DataNotExistsException;
import org.example.exceptions.DataNotInsertedException;
import org.example.model.entity_models.ProductModel;
import spark.Request;

import java.util.List;

import static org.example.constants.Constants.REQUEST_COMPLETED;
import static org.example.constants.Constants.WHERE_NAME;

public class ProductValidation {

    private final DatabaseQuery databaseQuery = new DatabaseQuery();

    /**
     * Validates whether product input data is complete.
     */
    public String dataNotInserted(Request request) {
        String name = request.queryParams("name");
        String price = request.queryParams("price");

        if (name.isEmpty() || price.isEmpty()) {
            String message = "Please insert all requested data!";
            throw new DataNotInsertedException(message);
        } else {
            return REQUEST_COMPLETED;
        }
    }

    /**
     * Validates whether the inserted product name is already in use or free.
     */
    public String nameAlreadyExists(Request request) {
        String name = request.queryParams("name");
        String condition = WHERE_NAME + name + "'";

        List<Object> queryByEmail = databaseQuery.getByAttribute(ProductModel.TABLE_NAME, condition);

        if (queryByEmail.isEmpty()) {
            return REQUEST_COMPLETED;
        } else {
            String message = name + " already exists!";
            throw new DataExistsException(message);
        }
    }

    /**
     * Validates whether there are products in the database to return.
     */
    public String dataNotExists() {
        List<Object> queryAll = databaseQuery.getAll(ProductModel.TABLE_NAME);

        if (queryAll.isEmpty()) {
            String message = "No products in the database!";
            throw new DataNotExistsException(message);
        } else {
            return REQUEST_COMPLETED;
        }
    }
}
