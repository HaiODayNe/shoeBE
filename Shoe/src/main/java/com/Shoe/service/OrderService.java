package com.Shoe.service;

import com.Shoe.model.cart.Cart;
import com.Shoe.model.order.Order;

public interface OrderService {
    //1. tạo đơn từ giỏ hàng
    //2. Cập nhật trạng thái đơn hàng
    //3. theo dõi trạng thái đơn hàng theo sđt
    //4. huỷ đơn hàng
    Order createOrder(Cart cart);

    Order updateStatus(String orderCode);

    Order getStatusByPhoneNumber(String phoneNumber);

    boolean cancelOrder(Order order);

}
