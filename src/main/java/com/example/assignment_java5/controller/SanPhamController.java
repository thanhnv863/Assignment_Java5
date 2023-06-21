package com.example.assignment_java5.controller;

import com.example.assignment_java5.enitityRequest.SanPhamRequest;
import com.example.assignment_java5.entity.DeGiay;
import com.example.assignment_java5.entity.SanPham;
import com.example.assignment_java5.service.SanPhamService;
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
@RequestMapping("/san-pham")
public class SanPhamController {
    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping()
    public String hienThi(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model) {
        if (pageNo < 0) {
            pageNo = 0;
        }
        model.addAttribute("listSP", sanPhamService.fillAll(pageNo, 5));
        model.addAttribute("sanPham", new DeGiay());
        return "/viewSanPham/san-pham";
    }

    @PostMapping("/addOrUpdate")
    public String addOrUpdate(Model model,
                              @Valid @ModelAttribute("sanPham") SanPhamRequest sanPham,
                              BindingResult bindingResult,
                              @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo) {

        if (pageNo < 0) {
            pageNo = 0;
        }
//        SanPham checkMa = sanPhamService.findByMa(sanPham.getMa());
//        if (checkMa != null) {
//            model.addAttribute("checkMa", "Mã đã tồn tại vui lòng nhập mã khác");
//            model.addAttribute("listSP", sanPhamService.fillAll(0, 5));
//            return "/viewSanPham/san-pham";
//        } else
            if (bindingResult.hasErrors()) {
            model.addAttribute("listSP", sanPhamService.fillAll(0, 5));
            return "/viewSanPham/san-pham";
        } else {
            sanPhamService.addOrUpdate(sanPham);
            model.addAttribute("listSP", sanPhamService.fillAll(pageNo, 5));
            return "redirect:/san-pham";
        }
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") UUID id,
                         Model model) {
        sanPhamService.delete(id);
        return "redirect:/san-pham";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") UUID id,
                       @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model) {
        if (pageNo < 0) {
            pageNo = 0;
        }
        model.addAttribute("sanPham", sanPhamService.getById(id));
        model.addAttribute("listSP", sanPhamService.fillAll(pageNo, 5));
        return "/viewSanPham/san-pham";
    }
}
