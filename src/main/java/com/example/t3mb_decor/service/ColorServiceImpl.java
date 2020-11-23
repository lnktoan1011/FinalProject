package com.example.t3mb_decor.service;

import com.example.t3mb_decor.model.Color;
import com.example.t3mb_decor.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ColorServiceImpl implements ColorService{

    @Autowired
    private ColorRepository colorRepository;
    @Override
    public List<Color> getAllColor() {
        return colorRepository.findAll();
    }

    @Override
    public void saveColor(Color color) {
        this.colorRepository.save(color);
    }

    @Override
    public Color getColor(long id) {
        return this.colorRepository.findById(id).get();
    }

    @Override
    public void deleteColor(long id) { this.colorRepository.deleteById(id);
    }

    @Override
    public void updateColor(Color color) {
        long id = color.getId();
        Color colorUpdate = this.getColor(id);
        Date createDate = colorUpdate.getCreatedAt();

        colorUpdate.setCreatedAt(createDate);
        colorUpdate.setName(color.getName());
        this.colorRepository.save(colorUpdate);
    }

    @Override
    public List<Color> getColorSort() {
        List<Color> list = colorRepository.findByOrderByIdDesc();
        return list;
    }
}
