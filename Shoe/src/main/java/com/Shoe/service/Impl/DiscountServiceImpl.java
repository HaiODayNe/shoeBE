package com.Shoe.service.Impl;

import com.Shoe.model.discount.Discount;
import com.Shoe.service.DiscountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {
    @Override
    public List<Discount> getDiscounts() {
        return List.of();
    }

    @Override
    public Boolean checkDiscount(Discount discount) {
        return null;
    }

    @Override
    public BigDecimal getDiscountAmount(Discount discount) {
        return null;
    }

    @Override
    public Discount getDiscount(String code) {
        return null;
    }
}
