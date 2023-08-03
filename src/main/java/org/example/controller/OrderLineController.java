package org.example.controller;

import org.example.model.entity_models.OrderLineModel;
import org.example.service.OrderLineService;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Long.parseLong;

public class OrderLineController extends Controller{

    private final OrderLineService orderLineService = new OrderLineService();

    /**
     * Inserts new order line with selected product and order id.
     */
    public String insert(Request request, @SuppressWarnings("unused")Response response) {
        OrderLineModel orderLineModel = new OrderLineModel();

        Long id = parseLong(request.params(":id"));
        Object orderId = request.session().attribute("attribute");

        orderLineModel.setCustomerOrderId((Long) orderId);
        orderLineModel.setProductId(id);
        orderLineService.insert(orderLineModel);

        Map<String, Object> model = new HashMap<>();
        model.put("order_line", orderLineModel);

        return render("order_line/order_line.hbs", model);
    }
}
