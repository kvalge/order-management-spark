package org.example.mapper;

import org.example.model.ProductModel;
import org.example.model.ProductViewModel;
import spark.Request;

import java.util.ArrayList;
import java.util.List;
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

    public ProductViewModel entityToRequest(Object productObject) {
        return getProductViewModel(productObject);
    }

    public List<ProductViewModel> toRequestList(List<Object> productObjectList) {
        List<ProductViewModel> productViewModels = new ArrayList<>();

        for (Object object : productObjectList) {
            ProductViewModel productViewModel = getProductViewModel(object);
            productViewModels.add(productViewModel);
        }
        return productViewModels;
    }

    private static ProductViewModel getProductViewModel(Object object) {
        String[] objectSplit = object.toString().split(",");

        ProductViewModel productViewModel = new ProductViewModel();
        productViewModel.setId(Long.valueOf(objectSplit[0].substring(1)));
        productViewModel.setName(objectSplit[1]);
        productViewModel.setSku_code(objectSplit[2]);
        productViewModel.setPrice(objectSplit[3].substring(0, 6));

        return productViewModel;
    }
}
