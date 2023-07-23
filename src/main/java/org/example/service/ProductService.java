package org.example.service;

import org.example.mapper.ProductMapper;
import org.example.model.ProductModel;
import org.example.model.ProductViewModel;
import org.example.repository.ProductRepository;
import spark.Request;

import java.util.List;

import static java.lang.Long.parseLong;

public class ProductService {

    ProductMapper productMapper = new ProductMapper();
    ProductRepository productRepository = new ProductRepository();

    public void insert(Request request) {
        ProductModel product = productMapper.requestToEntity(request);

        productRepository.insert(product);
    }

    public List<ProductViewModel> getAll() {
        List<Object> productObjecList = productRepository.getAll();

        return productMapper.toRequestList(productObjecList);
    }

    public ProductViewModel getById(Request request) {
        Object productObject = productRepository.getById(request);

        return productMapper.entityToRequest(productObject);
    }

    public void update(Request request) {
        ProductModel product = productMapper.requestToEntity(request);
        Long id = parseLong(request.params(":id"));

        productRepository.update(product, id);
    }

    public void delete(Long id) {
        productRepository.delete(id);
    }
}
