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

    public List<ProductViewModel> toRequestList(List<ProductModel> productModels) {
        List<ProductViewModel> productViewModels = new ArrayList<>();

        for (ProductModel productModel : productModels) {
            ProductViewModel productViewModel = new ProductViewModel();
            productViewModel.setId(productModel.getId());
            productViewModel.setName(productModel.getName());
            productViewModel.setSku_code(productModel.getSku_code());
            productViewModel.setPrice(productModel.getPrice());
            productViewModels.add(productViewModel);
        }

        return productViewModels;
    }
}
