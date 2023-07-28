package org.example.service;

import org.example.mapper.ProductMapper;
import org.example.model.entity_models.ProductModel;
import org.example.model.view_models.ProductViewModel;
import org.example.repository.ProductRepository;
import spark.Request;

import java.util.List;

import static java.lang.Long.parseLong;

public class ProductService {

    private final ProductMapper productMapper = new ProductMapper();
    private final ProductRepository productRepository = new ProductRepository();

    /**
     * Converts Request type to ProductModel type in Mapper class for returning it to Repository class.
     */
    public void insert(Request request) {
        ProductModel product = productMapper.requestToEntity(request);

        productRepository.insert(product);
    }

    /**
     * Converts Object type list returned from database via Repository class to ProductViewModel type list
     * in Mapper class.
     */
    public List<ProductViewModel> getAll() {
        List<Object> productObjecList = productRepository.getAll();

        return productMapper.toRequestList(productObjecList);
    }

    /**
     * Converts Object type returned by id from the database via Repository class to ProductViewModel type
     * in Mapper class.
     */
    public ProductViewModel getById(Request request) {
        Object productObject = productRepository.getById(request);

        return productMapper.entityToRequest(productObject);
    }

    /**
     * Converts Request type to ProductModel type in Mapper class for returning it to Repository class.
     */
    public void update(Request request) {
        ProductModel product = productMapper.requestToEntity(request);
        Long id = parseLong(request.params(":id"));

        productRepository.update(product, id);
    }

    public void delete(Long id) {
        productRepository.delete(id);
    }
}
