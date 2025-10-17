package com.Shoe.service.Impl;

import com.Shoe.model.cart.Cart;
import com.Shoe.model.order.Order;
import com.Shoe.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public Order createOrder(Cart cart) {
        return null;
    }

    @Override
    public Order updateStatus(String orderCode) {
        return null;
    }

    @Override
    public Order getStatusByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public boolean cancelOrder(Order order) {
        return false;
    }
}
