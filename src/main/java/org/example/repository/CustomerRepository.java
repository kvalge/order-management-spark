package org.example.repository;

import org.example.database.DatabaseQuery;
import org.example.model.CustomerModel;

public class CustomerRepository {

    DatabaseQuery databaseQuery = new DatabaseQuery();

    public void save(CustomerModel customerModel) {
        databaseQuery.save(CustomerModel.TABLENAME, customerModel);
    }
}
