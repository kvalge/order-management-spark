package org.example.repository;

import org.example.database.DatabaseQuery;
import org.example.model.ProductModel;
import spark.Request;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Long.parseLong;

public class ProductRepository {

    private final DatabaseQuery databaseQuery = new DatabaseQuery();

    public void insert(ProductModel productModel) {
        databaseQuery.insert(ProductModel.TABLE_NAME, createProductMap(productModel));
    }

    public List<Object> getAll() {
        return databaseQuery.getAll(ProductModel.TABLE_NAME);
    }

    public Object getById(Request request) {
        Long id = parseLong(request.params(":id"));

        return databaseQuery.getById(ProductModel.TABLE_NAME, id);
    }

    public void update(ProductModel productModel, Long id) {
        databaseQuery.update(ProductModel.TABLE_NAME, createProductMap(productModel), id);
    }

    public void delete(Long id) {
        databaseQuery.delete(ProductModel.TABLE_NAME, id);
    }

    private Map<String, Object> createProductMap(ProductModel product) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();

        map.put("name", product.getName());
        map.put("sku_code", product.getSku_code());
        map.put("price", product.getPrice());

        return map;
    }
}
