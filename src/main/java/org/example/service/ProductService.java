package org.example.service;

import org.example.mapper.ProductMapper;
import org.example.model.CustomerModel;
import org.example.model.ProductModel;
import org.example.repository.ProductRepository;
import spark.Request;

public class ProductService {

    ProductMapper productMapper = new ProductMapper();
    ProductRepository productRepository = new ProductRepository();

    public void insert(Request request) {
        ProductModel product = productMapper.requestToEntity(request);
        productRepository.insert(product);
    }
}
