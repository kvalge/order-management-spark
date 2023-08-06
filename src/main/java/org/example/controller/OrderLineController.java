package org.example.controller;

import org.example.model.entity_models.OrderLineModel;
import org.example.model.view_models.OrderLineViewModel;
import org.example.model.view_models.ProductViewModel;
import org.example.service.OrderLineService;
import org.example.service.ProductService;
import spark.Request;
import spark.Response;

import java.text.DecimalFormat;
import java.util.*;

import static java.lang.Long.parseLong;

public class OrderLineController extends Controller {

    private final OrderLineService orderLineService = new OrderLineService();
    private final ProductService productService = new ProductService();

    /**
     * Inserts the new order line with the selected product and the order id.
     * Inserts to the template model:
     *  just inserted new product;
     *  all ordered products of the order line by its id;
     *  total price of ordered products.
     */
    public String insert(Request request, @SuppressWarnings("unused") Response response) {
        OrderLineModel orderLineModel = new OrderLineModel();

        Long productId = parseLong(request.params(":id"));
        Object orderId = request.session().attribute("attribute");

        orderLineModel.setCustomerOrderId((Long) orderId);
        orderLineModel.setProductId(productId);
        orderLineService.insert(orderLineModel);

        ProductViewModel product = productService.getById(productId);

        Map<String, Object> model = new HashMap<>();
        model.put("product", product);

        List<OrderLineViewModel> orderLines = getByOrderId((Long) orderId);

        List<ProductViewModel> productViewModels = getProductViewModels(orderLines);
        model.put("products", productViewModels);

        String totalPrice = getTotalPrice(productViewModels);
        model.put("total", totalPrice);

        return render("order_line/order_line.hbs", model);
    }

    public List<OrderLineViewModel> getByOrderId(Long orderId) {
        return orderLineService.getByOrderId(orderId);
    }

    /**
     * Returns list of products completed of every order line product of a certain order.
     */
    private List<ProductViewModel> getProductViewModels(List<OrderLineViewModel> orderLines) {
        List<ProductViewModel> productViewModels = new ArrayList<>();

        for (OrderLineViewModel viewModel : orderLines) {
            Long prodId = viewModel.getProductId();
            ProductViewModel productViewModel = productService.getById(prodId);
            productViewModels.add(productViewModel);
        }
        return productViewModels;
    }

    private String getTotalPrice(List<ProductViewModel> productViewModels) {
        float totalPrice = 0;

        for (ProductViewModel productViewModel : productViewModels) {
            String price = productViewModel.getPrice();
            float floatPrice = Float.parseFloat(price);
            totalPrice = totalPrice + floatPrice;
        }
        DecimalFormat df = new DecimalFormat("#.##");

        return df.format(totalPrice);
    }
}
