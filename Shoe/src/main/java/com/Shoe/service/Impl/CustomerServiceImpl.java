package com.Shoe.service.Impl;

import com.Shoe.model.customer.CustomerInfo;
import com.Shoe.model.order.Order;
import com.Shoe.service.CustomerService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerInfo getCustomerInfo(String phoneNumber) {
        return null;
    }

    @Override
    public List<Order> orderStatus(String phoneNumber) {
        return List.of();
    }

    @Override
    public BigDecimal getTotal(String phoneNumber) {
        return null;
    }

    @Override
    public CustomerInfo editCustomerInfo(String phoneNumber) {
        return null;
    }
}
