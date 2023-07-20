package org.example.database;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
