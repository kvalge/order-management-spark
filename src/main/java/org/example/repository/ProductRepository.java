package org.example.repository;

import org.example.database.DatabaseQuery;
import org.example.model.ProductModel;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository {

    DatabaseQuery databaseQuery = new DatabaseQuery();

    public void insert(ProductModel productModel) {
        databaseQuery.insert(ProductModel.TABLE_NAME, createProductMap(productModel));
    }

    public List<ProductModel> getAll() {
        return databaseQuery.getAll(ProductModel.TABLE_NAME);
    }

    private Map<String, Object> createProductMap(ProductModel product) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();

        map.put("name", product.getName());
        map.put("sku_code", product.getSku_code());
        map.put("price", product.getPrice());

        return map;
    }
}
