package com.Shoe.service;

import com.Shoe.model.cart.Cart;
import com.Shoe.model.order.Order;
import com.Shoe.model.product.Product;

public interface CartService {
    //1. thêm giỏ hàng người dùng
//2. sửa giỏ hàng
//3. xoá giỏ hàng
//4. tính tổng số tiền
//5. chuyển giỏ hàng thành đơn hàng
    Cart getCartByUserId(Long userId);

    Cart addItemToCart();

    Cart editItemToCart();

    Cart removeItemFromCart();

    Double totalAmount(double cartTotalAmount);

    Order order(Cart cart);

}
