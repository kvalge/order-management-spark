package org.example.repository;

import org.example.database.Configuration;
import org.example.model.CustomerModel;

import java.sql.SQLException;
import java.sql.Statement;

import static org.example.constants.Constants.*;

public class CustomerRepository {

    Configuration configuration = new Configuration();

    public void save(CustomerModel customerModel) {

        try {
            Statement statement = configuration.connect().createStatement();
            statement.executeUpdate(
                    "INSERT INTO " + TABLE_CUSTOMER +
                            "(id, full_name, registration_code, email, telephone)" +
                            "VALUES (1, '" +
                            customerModel.getFullName() + "', '" +
                            customerModel.getRegistrationCode() + "', '" +
                            customerModel.getEmail() + "', '" +
                            customerModel.getTelephone() + "')");
        } catch (SQLException e) {
            System.out.println(QUERY_FAILED + e.getMessage());
            e.getStackTrace();
        }
    }
}
