package org.example.database;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.String.join;
import static java.util.stream.Collectors.toList;
import static org.example.constants.Constants.*;

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
        List<String> keys = map.keySet().stream().sorted().collect(toList());
        String mapKeys = join(", ", keys);

        List<String> values = new ArrayList<>();
        map.forEach((k, v) -> values.add("'" + v.toString() + "'"));
        String valueKeys = join(", ", values);

        return "INSERT INTO " + table + " (id, " + mapKeys + ") " +
                "VALUES (DEFAULT, " + valueKeys + ")";
    }
}
