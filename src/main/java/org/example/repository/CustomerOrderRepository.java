package org.example.repository;

import org.example.database.DatabaseQuery;
import org.example.model.entity_models.CustomerOrderModel;

import java.util.LinkedHashMap;
import java.util.Map;

public class CustomerOrderRepository {

    private final DatabaseQuery databaseQuery = new DatabaseQuery();

    public void insert(CustomerOrderModel orderModel) {
        databaseQuery.insert(CustomerOrderModel.TABLE_NAME, createCustomerMap(orderModel));
    }

    private Map<String, Object> createCustomerMap(CustomerOrderModel orderModel) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();

        map.put("submission_date", orderModel.getSubmissionDate());
        map.put("customer_id", orderModel.getCustomerId());
        map.put("sku_code", orderModel.getSku_code());

        return map;
    }
}
