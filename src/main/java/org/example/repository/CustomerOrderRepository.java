package org.example.repository;

import org.example.database.DatabaseQuery;
import org.example.model.entity_models.CustomerOrderModel;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.example.constants.Constants.WHERE_SKU_CODE;

public class CustomerOrderRepository {

    private final DatabaseQuery databaseQuery = new DatabaseQuery();

    public void insert(CustomerOrderModel orderModel) {
        databaseQuery.insert(CustomerOrderModel.TABLE_NAME, createCustomerOrderMap(orderModel));
    }

    private Map<String, Object> createCustomerOrderMap(CustomerOrderModel orderModel) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();

        map.put("submission_date", orderModel.getSubmissionDate());
        map.put("customer_id", orderModel.getCustomerId());
        map.put("sku_code", orderModel.getSku_code());

        return map;
    }

    public Object getBySkuCode(String skuCode) {
        String condition = WHERE_SKU_CODE + skuCode + "'";

        return databaseQuery.getByAttribute(CustomerOrderModel.TABLE_NAME, condition);
    }
}
