package org.example.database;

import org.example.model.ProductModel;

import java.sql.ResultSet;
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

    public List<ProductModel> getAll(String table) {
        try {
            Statement statement = configuration.connect().createStatement();
            statement.execute("SELECT * FROM " + table);
            ResultSet results = statement.getResultSet();

            List<ProductModel> list = new ArrayList<>();

            while (results.next()) {
                ProductModel productModel = new ProductModel();
                productModel.setId(results.getLong("id"));
                productModel.setName(results.getString("name"));
                productModel.setSku_code(results.getString("sku_code"));
                productModel.setPrice(results.getString("price"));
                list.add(productModel);
            }
            return list;
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
