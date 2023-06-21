package com.example.assignment_java5.controller;

import com.example.assignment_java5.enitityRequest.DeGiayRequest;
import com.example.assignment_java5.entity.DeGiay;
import com.example.assignment_java5.service.DeGiayService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/de-giay")
public class DeGiayController {

    @Autowired
    private DeGiayService deGiayService;

    @GetMapping()
    public String hienThi(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model) {
        if (pageNo < 0) {
            pageNo = 0;
        }
        model.addAttribute("listDG", deGiayService.fillAll(pageNo, 5));
        model.addAttribute("deGiay", new DeGiay());
        return "/viewDeGiay/de-giay";
    }

    @PostMapping("/addOrUpdate")
    public String addOrUpdate(Model model,
                              @Valid @ModelAttribute("deGiay") DeGiayRequest deGiay,
                              BindingResult bindingResult,
                              @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo) {

        if (pageNo < 0) {
            pageNo = 0;
        }
//        DeGiay checkMa = deGiayService.findByMa(deGiay.getMa());
//        if (checkMa != null) {
//            model.addAttribute("checkMa", "Mã đã tồn tại vui lòng nhập mã khác");
//            model.addAttribute("listDG", deGiayService.fillAll(0, 5));
//            return "/viewDeGiay/de-giay";
//        } else
            if (bindingResult.hasErrors()) {
            model.addAttribute("listDG", deGiayService.fillAll(0, 5));
            return "/viewDeGiay/de-giay";
        } else {
            deGiayService.addOrUpdate(deGiay);
            model.addAttribute("listDG", deGiayService.fillAll(pageNo, 5));
            return "redirect:/de-giay";
        }
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") UUID id,
                         Model model) {
        deGiayService.delete(id);
        return "redirect:/de-giay";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") UUID id,
                       @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model) {
        if (pageNo < 0) {
            pageNo = 0;
        }
        model.addAttribute("deGiay", deGiayService.getById(id));
        model.addAttribute("listDG", deGiayService.fillAll(pageNo, 5));
        return "/viewDeGiay/de-giay";
    }
}
