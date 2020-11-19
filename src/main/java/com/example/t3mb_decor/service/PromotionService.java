package com.example.t3mb_decor.service;

import com.example.t3mb_decor.model.Discount;

import java.util.List;

public interface PromotionService {
    void savePro(Discount discount);
    List<Discount> getList();
    void deletePro(long id);
    Discount getPro(long id);
    void updatePro(Discount discount);
}
