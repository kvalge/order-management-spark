package org.example.service;

import org.example.mapper.OrderLineMapper;
import org.example.model.entity_models.OrderLineModel;
import org.example.model.view_models.OrderLineViewModel;
import org.example.repository.OrderLineRepository;

import java.util.List;

public class OrderLineService {

    private final OrderLineRepository orderLineRepository = new OrderLineRepository();
    private final OrderLineMapper orderLineMapper = new OrderLineMapper();

    public void insert(OrderLineModel orderLineModel) {
        orderLineRepository.insert(orderLineModel);
    }

    public List<OrderLineViewModel> getByOrderId(Long orderId) {

        List<Object> byOrderId = orderLineRepository.getListByOrderId(orderId);

        return orderLineMapper.toViewModelList(byOrderId);
    }
}
