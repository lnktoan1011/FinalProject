package com.example.t3mb_decor.controller.admin;

import com.example.t3mb_decor.model.Discount;
import com.example.t3mb_decor.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.Vector;

@Controller
@RequestMapping("admins/promotion")
public class PromotionController {
    @Autowired
    PromotionService promotionService;

    @ModelAttribute("first")
    public String getActive1(){
        return ".catalog_click";
    }
    @ModelAttribute("second")
    public String getActive2(){
        return ".promotion_click";
    }
    @ModelAttribute("Pro")
    public Discount showPro(){
        return new Discount();
    }
    @ModelAttribute("listPro")
    public List<Discount> showList(){
        return promotionService.getList();
    }
    Random random = new Random();
    Vector v = new Vector();
    @GetMapping
    public String viewPromotion(Model model){

        return "content/admin/promotion";
    }

    @PostMapping("add")
    public String addPro(@ModelAttribute("Pro") Discount discount,Model model){
        if (discount.getId()!=0){
            promotionService.updatePro(discount);
            model.addAttribute("Pro",new Discount());
            return "redirect:/admins/promotion?successUpdate";
        }

        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 8;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
//        int randomWintNextIntWithinARange = random.nextInt(899999) + 100000;
        if (v.contains(generatedString)) {
            System.out.println("Code is use");
            return "redirect: /admins/promotion?errorAdd";
        }
        v.add(generatedString);
        System.out.println("v: "+v);
        System.out.println("code: "+generatedString);

        discount.setName(generatedString);
        discount.setStatus(1);
        promotionService.savePro(discount);
        return "redirect:/admins/promotion?successAdd";
    }
    @GetMapping("/delete/{id}")
    public String deletePro(@PathVariable("id") long id){
        promotionService.deletePro(id);
        return "redirect:/admins/promotion";
    }

    @GetMapping("/update/{id}")
    public String updatePro(@PathVariable("id") long id, Model model){
        Discount discount = promotionService.getPro(id);
        model.addAttribute("Pro",discount);
        model.addAttribute("showUp",1);
        return "content/admin/promotion";
    }

}
