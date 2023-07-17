package org.example.database;

import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {

    Configuration configuration = new Configuration();
    public static final String QUERY_FAILED = "Query failed: ";

    public static final String TABLE_CUSTOMER = "customer";

    public void addTables() {
        try {
            Statement statement = configuration.connect().createStatement();

            statement.execute("DROP TABLE IF EXISTS " + TABLE_CUSTOMER);

            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_CUSTOMER +
                    " (id INTEGER PRIMARY KEY, full_name TEXT, registration_code TEXT, email TEXT, telephone TEXT)");
        } catch (SQLException e) {
            System.out.println(QUERY_FAILED + e.getMessage());
            e.getStackTrace();
        }
    }
}
