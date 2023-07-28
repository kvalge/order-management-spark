package org.example.service;

import org.example.model.entity_models.CustomerOrderModel;
import org.example.repository.CustomerOrderRepository;

public class CustomerOrderService {

    CustomerOrderRepository orderRepository = new CustomerOrderRepository();

    public void insert(CustomerOrderModel orderModel) {
        orderRepository.insert(orderModel);
    }
}
