package org.example.mapper;

import org.example.model.view_models.OrderLineViewModel;

import java.util.ArrayList;
import java.util.List;

public class OrderLineMapper {

    /**
     * Converts the Object List (returned from the database) to the OrderLineViewModel list.
     */
    public List<OrderLineViewModel> toViewModelList(List<Object> orderLineObjectList) {
        List<OrderLineViewModel> orderLineViewModels = new ArrayList<>();

        for (Object object : orderLineObjectList) {
            OrderLineViewModel viewModel = getOrderLineViewModel(object);
            orderLineViewModels.add(viewModel);
        }
        return orderLineViewModels;
    }

    /**
     * Converts the Object type (returned from database) to the OrderLineViewModel.
     */
    private static OrderLineViewModel getOrderLineViewModel(Object object) {
        String[] objectSplit = object.toString().split(",");

        OrderLineViewModel viewModel = new OrderLineViewModel();
        viewModel.setId(Long.valueOf(objectSplit[0].substring(1)));
        viewModel.setCustomerOrderId(Long.valueOf(objectSplit[1].substring(1)));
        viewModel.setProductId(Long.valueOf(objectSplit[2].substring(1, 2)));

        return viewModel;
    }
}
