package com.example.t3mb_decor.controller.admin;


import com.example.t3mb_decor.model.Color;
import com.example.t3mb_decor.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("admins/color")
@Controller
public class ColorController {
    @Autowired
    private ColorService colorService;

    @ModelAttribute("first")
    public String getActive1(){
        return ".catalog_click";
    }
    @ModelAttribute("second")
    public String getActive2(){
        return ".color_click";
    }

    @ModelAttribute("listColors")
    public List<Color> getList(){
        List<Color> listColor =  colorService.getAllColor();
        return listColor;
    }

    @GetMapping
    public String viewAllColor(Model model){
        model.addAttribute("color", new Color());
        return "content/admin/color";
    }
    @GetMapping("delete/{id}")
    public String deleteColor(@PathVariable("id") long id){
        colorService.deleteColor(id);
        return "redirect:/admins/color";
    }

    @PostMapping("add")
    public String saveColor(@ModelAttribute("color") Color color){

        if(color.getId() != 0) {
            colorService.updateColor(color);
            return "redirect:/admins/color/update/" + color.getId() ;
        }
        colorService.saveColor(color);
        return "redirect:" ;
    }
    @GetMapping("update/{id}")
    public String viewUpdateColor(@PathVariable("id") long id, Model model){
        Color colorUpdate = colorService.getColor(id);
        model.addAttribute("color", colorUpdate);
        return "content/admin/color";
    }
}
