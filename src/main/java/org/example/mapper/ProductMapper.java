package org.example.mapper;

import org.example.model.entity_models.ProductModel;
import org.example.model.view_models.ProductViewModel;
import spark.Request;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductMapper {

    /**
     * Converts the Request type (returned from the template form) to the ProductModel attributes.
     */
    public ProductModel requestToEntity(Request request) {
        ProductModel product = new ProductModel();

        product.setName(request.queryParams("name"));
        UUID uuid = UUID.randomUUID();
        product.setSku_code(uuid.toString());
        product.setPrice(request.queryParams("price"));
        product.setImage(request.queryParams("image"));

        return product;
    }

    /**
     * Converts the Object type to the ProductViewModel type.
     */
    public ProductViewModel entityToViewModel(Object productObject) {
        return getProductViewModel(productObject);
    }

    /**
     * Converts the Object List (returned from the database) to the ProductViewModel list.
     */
    public List<ProductViewModel> toViewModelList(List<Object> productObjectList) {
        List<ProductViewModel> productViewModels = new ArrayList<>();

        for (Object object : productObjectList) {
            ProductViewModel productViewModel = getProductViewModel(object);
            productViewModels.add(productViewModel);
        }
        return productViewModels;
    }

    /**
     * Converts the Object type (returned from database) to
     * the ProductViewModel attributes.
     */
    private static ProductViewModel getProductViewModel(Object object) {
        String[] objectSplit = object.toString().split(",");

        ProductViewModel productViewModel = new ProductViewModel();
        productViewModel.setId(Long.valueOf(objectSplit[0].substring(1)));
        productViewModel.setName(objectSplit[1]);
        productViewModel.setSku_code(objectSplit[2]);
        productViewModel.setPrice(objectSplit[3].substring(0, 6));
        productViewModel.setImage(objectSplit[4].substring(1,objectSplit[4].length()-1));

        return productViewModel;
    }
}
