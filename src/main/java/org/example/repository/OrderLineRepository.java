package org.example.repository;

import org.example.database.DatabaseQuery;
import org.example.model.entity_models.OrderLineModel;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.example.constants.Constants.WHERE_CUSTOMER_ORDER_ID;

public class OrderLineRepository {

    private final DatabaseQuery databaseQuery = new DatabaseQuery();

    public void insert(OrderLineModel orderLineModel) {
        databaseQuery.insert(OrderLineModel.TABLE_NAME, createOrderLineMap(orderLineModel));
    }

    private Map<String, Object> createOrderLineMap(OrderLineModel orderLineModel) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();

        map.put("customer_order_id", orderLineModel.getCustomerOrderId());
        map.put("product_id", orderLineModel.getProductId());

        return map;
    }

    public List<Object> getListByOrderId(Long id) {
        String condition = WHERE_CUSTOMER_ORDER_ID + id;

        return databaseQuery.getListByAttribute(OrderLineModel.TABLE_NAME, condition);
    }
}
