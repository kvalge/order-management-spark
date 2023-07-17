package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Configuration {


    public static final String DATABASE_NAME = "order_spark";
    public static final String URL = "jdbc:postgresql://localhost:5432/" + DATABASE_NAME;
    public static final String USER = "postgres";
    public static final String PASSWORD = "postgres";

    public Connection connect() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }
}
