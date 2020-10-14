package com.example.t3mb_decor.service;


import com.example.t3mb_decor.model.Color;

import java.util.List;

public interface ColorService {
    List<Color> getAllColor();
    void saveColor(Color color);
    Color getColor(long id);
    void deleteColor(long id);
    void updateColor(Color color);
}
