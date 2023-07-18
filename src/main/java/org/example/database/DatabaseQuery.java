package org.example.database;

import org.example.model.CustomerModel;

import java.sql.SQLException;
import java.sql.Statement;

import static org.example.constants.Constants.*;

public class DatabaseQuery {

    Configuration configuration = new Configuration();

    public <T> void save(String table, CustomerModel customerModel) {
        try {
            Statement statement = configuration.connect().createStatement();
            statement.executeUpdate(
                    "INSERT INTO " + table +
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
