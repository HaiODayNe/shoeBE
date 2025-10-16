package com.Shoe.service;

import com.Shoe.model.discount.Discount;

import java.math.BigDecimal;
import java.util.List;

public interface DiscountService {
    //1. lấy danh sách mã giảm giá
//2. kiểm tra tính hợp lệ của mã
//3. Tính toán giá sau giảm
//4. quản lý thời hạn, số lần sử dụng
    List<Discount> getDiscounts();

    Boolean checkDiscount(Discount discount);

    BigDecimal getDiscountAmount(Discount discount);
    Discount getDiscount(String code);
}
