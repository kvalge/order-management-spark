package org.example.controller;

import org.example.model.entity_models.OrderLineModel;
import org.example.model.view_models.OrderLineViewModel;
import org.example.model.view_models.ProductViewModel;
import org.example.service.OrderLineService;
import org.example.service.ProductService;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Long.parseLong;

public class OrderLineController extends Controller {

    private final OrderLineService orderLineService = new OrderLineService();
    private final ProductService productService = new ProductService();

    /**
     * Inserts new order line with selected product and order id.
     */
    public String insert(Request request, @SuppressWarnings("unused") Response response) {
        OrderLineModel orderLineModel = new OrderLineModel();

        Long id = parseLong(request.params(":id"));
        Object orderId = request.session().attribute("attribute");

        orderLineModel.setCustomerOrderId((Long) orderId);
        orderLineModel.setProductId(id);
        orderLineService.insert(orderLineModel);

        ProductViewModel product = productService.getById(request);

        Map<String, Object> model = new HashMap<>();
        model.put("product", product);

        List<OrderLineViewModel> orderLines = getByOrderId((Long) orderId);
        model.put("order_line", orderLines);

        return render("order_line/order_line.hbs", model);
    }

    public List<OrderLineViewModel> getByOrderId(Long orderId) {
        return orderLineService.getByOrderId(orderId);
    }
}
