package com.Shoe.service;

import com.Shoe.model.customer.CustomerInfo;
import com.Shoe.model.order.Order;

import java.math.BigDecimal;
import java.util.List;

public interface CustomerService {
//1. lấy thông tin/kiểm tra lịch sử người đặt hàng theo sđt
//+ check trạng thái đơn hàng
//+ kiểm tra doanh số
//2. chỉnh sửa thông tin người đặt hàng
    CustomerInfo getCustomerInfo(String phoneNumber);
   List <Order> orderStatus(String phoneNumber);
    BigDecimal getTotal(String phoneNumber);
    CustomerInfo editCustomerInfo(String phoneNumber);
}
