package org.example.mapper;

import org.example.model.ProductModel;
import spark.Request;

import java.util.UUID;

public class ProductMapper {

    public ProductModel requestToEntity(Request request) {
        ProductModel product = new ProductModel();

        product.setName(request.queryParams("name"));
        UUID uuid = UUID.randomUUID();
        product.setSku_code(uuid.toString());
        product.setPrice(request.queryParams("price"));

        return product;
    }
}
