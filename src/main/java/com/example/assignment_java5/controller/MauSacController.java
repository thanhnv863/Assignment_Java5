package com.example.assignment_java5.controller;

import com.example.assignment_java5.enitityRequest.MauSacRequest;
import com.example.assignment_java5.entity.MauSac;
import com.example.assignment_java5.service.MauSacService;
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
@RequestMapping("/mau-sac")
public class MauSacController {
    @Autowired
    private MauSacService mauSacService;

    @GetMapping()
    public String hienThi(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model) {
        if (pageNo < 0) {
            pageNo = 0;
        }
        model.addAttribute("listMS", mauSacService.fillAll(pageNo, 5));
        model.addAttribute("mauSac", new MauSac());
        return "/viewMauSac/mau-sac";
    }

    @PostMapping("/addOrUpdate")
    public String addOrUpdate(Model model,
                              @Valid @ModelAttribute("mauSac") MauSacRequest mauSac,
                              BindingResult bindingResult,
                              @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo) {

        if (pageNo < 0) {
            pageNo = 0;
        }
        MauSac checkMa = mauSacService.findByMa(mauSac.getMa());
//        if (checkMa != null) {
//            model.addAttribute("checkMa", "Mã đã tồn tại vui lòng nhập mã khác");
//            model.addAttribute("listMS", mauSacService.fillAll(0, 5));
//            return "/viewMauSac/mau-sac";
//        } else
            if (bindingResult.hasErrors()) {
            model.addAttribute("listMS", mauSacService.fillAll(0, 5));
            return "/viewMauSac/mau-sac";
        } else {
            mauSacService.addOrUpdate(mauSac);
            model.addAttribute("listMS", mauSacService.fillAll(pageNo, 5));
            return "redirect:/mau-sac";
        }
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") UUID id,
                         Model model) {
        mauSacService.delete(id);
        return "redirect:/mau-sac";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") UUID id,
                       @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model) {
        if (pageNo < 0) {
            pageNo = 0;
        }
        model.addAttribute("mauSac", mauSacService.getById(id));
        model.addAttribute("listMS", mauSacService.fillAll(pageNo, 5));
        return "/viewMauSac/mau-sac";
    }
}
