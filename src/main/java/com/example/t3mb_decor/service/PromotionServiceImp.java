package com.example.t3mb_decor.service;

import com.example.t3mb_decor.model.Discount;
import com.example.t3mb_decor.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PromotionServiceImp implements PromotionService {
    @Autowired
    PromotionRepository promotionRepository;

    @Override
    public void savePro(Discount discount) {
        this.promotionRepository.save(discount);
    }

    @Override
    public List<Discount> getList() {
        return this.promotionRepository.findAll();
    }

    @Override
    public void deletePro(long id) {
        this.promotionRepository.deleteById(id);
    }

    @Override
    public Discount getPro(long id) {
        Discount discount = this.promotionRepository.findById(id).get();
        return discount;
    }

    @Override
    public void updatePro(Discount discount) {
        long id = discount.getId();
        Discount discountOld = getPro(id);
        discountOld.setPercent(discount.getPercent());
        Date createDate = discountOld.getCreatedAt();
        discountOld.setCreatedAt(createDate);
        this.promotionRepository.save(discountOld);
    }

    @Override
    public Discount getProbyName(String name) {
        Discount discount = this.promotionRepository.findByName(name);
        if(discount != null){
            return discount;
        }
        return null;
    }
}
