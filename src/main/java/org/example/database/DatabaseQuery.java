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

    public List<Object> getAll(String table) {
        try {
            Statement statement = configuration.connect().createStatement();
            statement.executeQuery("SELECT * FROM " + table);

            ResultSet results = statement.getResultSet();

            List<Object> records = new ArrayList<>();

            int cols = results.getMetaData().getColumnCount();
            while (results.next()) {
                List<Object> recordsList = new LinkedList<>();

                for (int i = 0; i < cols; i++) {
                    recordsList.add(results.getObject(i + 1));
                }
                records.add(recordsList);
            }
            return records;

        } catch (SQLException e) {
            System.out.println(QUERY_FAILED + e.getMessage());
            e.getStackTrace();
        }
        return null;
    }

    public Object getById(String table, Long id) {
        try {
            Statement statement = configuration.connect().createStatement();
            statement.executeQuery("SELECT * FROM " + table + " WHERE id = " + id);

            ResultSet results = statement.getResultSet();

            int cols = results.getMetaData().getColumnCount();
            List<Object> recordsList = new LinkedList<>();
            while (results.next()) {
                for (int i = 0; i < cols; i++) {
                    recordsList.add(results.getObject(i + 1));
                }
            }
            return recordsList;

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

    public void update(String table, Map<String, Object> map, Long id) {
        try {
            Statement statement = configuration.connect().createStatement();
            statement.executeUpdate(updateQuery(table, map, id));
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

    String updateQuery(String table, Map<String, Object> map, long id) {
        List<String> values = new ArrayList<>();

        map.forEach((k, v) -> {
            values.add("'" + v.toString() + "'");
        });

        String valueKeys = join(", ", values);

        return "UPDATE " + table + " SET " + valueKeys + "WHERE id = " + id;
    }
}
