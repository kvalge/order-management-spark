package org.example.mapper;

import org.example.model.view_models.CustomerOrderViewModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerOrderMapper {

    /**
     * Converts the Object type (returned from database) to
     * the CustomerOrderViewModel.
     */
    public CustomerOrderViewModel entityToCustomerOrderViewModel(Object object) throws ParseException {
        String[] objectSplit = object.toString().split(",");

        CustomerOrderViewModel customerOrderViewModel = new CustomerOrderViewModel();
        customerOrderViewModel.setId(Long.valueOf(objectSplit[0].substring(1)));

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(objectSplit[1]);
        customerOrderViewModel.setSubmissionDate(date);

        customerOrderViewModel.setCustomerId(Long.valueOf(objectSplit[2].substring(1)));
        customerOrderViewModel.setSku_code(objectSplit[3]);

        return customerOrderViewModel;
    }
}
