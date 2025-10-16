package com.Shoe.service.Impl;

import com.Shoe.model.cart.Cart;
import com.Shoe.model.order.Order;
import com.Shoe.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Override
    public Cart getCartByUserId(Long userId) {
        return null;
    }

    @Override
    public Cart addItemToCart() {
        return null;
    }

    @Override
    public Cart editItemToCart() {
        return null;
    }

    @Override
    public Cart removeItemFromCart() {
        return null;
    }

    @Override
    public Double totalAmount(double cartTotalAmount) {
        return 0.0;
    }

    @Override
    public Order order(Cart cart) {
        return null;
    }
}
