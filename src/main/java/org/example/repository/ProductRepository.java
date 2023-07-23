package org.example.repository;

import org.example.database.DatabaseQuery;
import org.example.model.ProductModel;

import java.util.*;

public class ProductRepository {

    DatabaseQuery databaseQuery = new DatabaseQuery();

    public void insert(ProductModel productModel) {
        databaseQuery.insert(ProductModel.TABLE_NAME, createProductMap(productModel));
    }

    public List<ProductModel> getAll() {
        List<Object> queryAll = databaseQuery.getAll(ProductModel.TABLE_NAME);

        List<ProductModel> productModelList = new ArrayList<>();
        for (Object object : queryAll) {
            String[] objectSplit = object.toString().split(",");
            ProductModel productModel = new ProductModel();
            productModel.setId(Long.valueOf(objectSplit[0].substring(1)));
            productModel.setName(objectSplit[1]);
            productModel.setSku_code(objectSplit[2]);
            productModel.setPrice(objectSplit[3].substring(0,6));
            productModelList.add(productModel);
        }
        return productModelList;
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
