package org.example.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.lang.String.join;
import static org.example.constants.Constants.QUERY_FAILED;

public class DatabaseQuery {

    Configuration configuration = new Configuration();

    public void insert(String table, Map<String, Object> map) {
        try {
            Statement statement = configuration.connect().createStatement();
            statement.executeUpdate(insertQuery(table, map));
        } catch (SQLException e) {
            System.out.println(QUERY_FAILED + e.getMessage());
            e.getStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getAll(String table) {
        try {
            Statement statement = configuration.connect().createStatement();
            statement.executeQuery("SELECT * FROM " + table);

            ResultSet results = statement.getResultSet();

            List<T> records = new ArrayList<>();

            int cols = results.getMetaData().getColumnCount();
            while (results.next()) {
                List<T> recordsList = new LinkedList<>();

                for (int i = 0; i < cols; i++) {
                    recordsList.add((T) (results.getObject(i + 1)));
                }
                records.add((T) recordsList);
            }
            return records;

        } catch (SQLException e) {
            System.out.println(QUERY_FAILED + e.getMessage());
            e.getStackTrace();
        }
        return null;
    }

    public void delete(String table, Long id) {
        try {
            Statement statement = configuration.connect().createStatement();
            statement.execute("DELETE FROM " + table + " WHERE id = " + id);
        } catch (SQLException e) {
            System.out.println(QUERY_FAILED + e.getMessage());
            e.getStackTrace();
        }
    }

    String insertQuery(String table, Map<String, Object> map) {
        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();

        map.forEach((k, v) -> {
            keys.add(k);
            values.add("'" + v.toString() + "'");
        });

        String mapKeys = join(", ", keys);
        String valueKeys = join(", ", values);

        return "INSERT INTO " + table + " (id, " + mapKeys + ") " +
                "VALUES (DEFAULT, " + valueKeys + ")";
    }
}
