package org.example.service;

import org.example.mapper.CustomerOrderMapper;
import org.example.model.entity_models.CustomerOrderModel;
import org.example.model.view_models.CustomerOrderViewModel;
import org.example.repository.CustomerOrderRepository;

import java.text.ParseException;

public class CustomerOrderService {

    CustomerOrderRepository customerOrderRepository = new CustomerOrderRepository();
    CustomerOrderMapper customerOrderMapper = new CustomerOrderMapper();

    public void insert(CustomerOrderModel orderModel) {
        customerOrderRepository.insert(orderModel);
    }

    public CustomerOrderViewModel getBySkuCode(String skuCode) throws ParseException {
        Object bySkuCode = customerOrderRepository.getBySkuCode(skuCode);

        return customerOrderMapper.entityToCustomerOrderViewModel(bySkuCode);
    }
}
