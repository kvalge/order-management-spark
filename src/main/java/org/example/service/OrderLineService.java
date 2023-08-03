package org.example.service;

import org.example.model.entity_models.OrderLineModel;
import org.example.repository.OrderLineRepository;

public class OrderLineService {

    private final OrderLineRepository orderLineRepository = new OrderLineRepository();

    public void insert(OrderLineModel orderLineModel) {
        orderLineRepository.insert(orderLineModel);
    }
}
