package org.example.service;

import org.example.mapper.ProductMapper;
import org.example.model.ProductModel;
import org.example.model.ProductViewModel;
import org.example.repository.ProductRepository;
import spark.Request;

import java.util.List;

public class ProductService {

    ProductMapper productMapper = new ProductMapper();
    ProductRepository productRepository = new ProductRepository();

    public void insert(Request request) {
        ProductModel product = productMapper.requestToEntity(request);

        productRepository.insert(product);
    }

    public List<ProductViewModel> getAll() {
        List<ProductModel> products = productRepository.getAll();

        return  productMapper.toRequestList(products);
    }

    public void delete(Long id) {
        productRepository.delete(id);
    }
}
