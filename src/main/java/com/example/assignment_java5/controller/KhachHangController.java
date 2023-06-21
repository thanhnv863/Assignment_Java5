package com.example.assignment_java5.controller;

import com.example.assignment_java5.enitityRequest.KhachHangRequest;
import com.example.assignment_java5.entity.ChucVu;
import com.example.assignment_java5.entity.KhachHang;
import com.example.assignment_java5.service.ChucVuService;
import com.example.assignment_java5.service.KhachHangService;
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
@RequestMapping("/khach-hang")
public class KhachHangController {
    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private ChucVuService chucVuService;

    @GetMapping()
    public String hienThi(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model) {
        if (pageNo < 0) {
            pageNo = 0;
        }
        model.addAttribute("listKH", khachHangService.fillAll(pageNo, 5));
        model.addAttribute("chucVu", chucVuService.getAllChucVu());
        model.addAttribute("khachHang", new KhachHang());
        return "/viewKhachHang/khach-hang";
    }

    @PostMapping("/addOrUpdate")
    public String addOrUpdate(Model model,
                              @Valid @ModelAttribute("khachHang") KhachHangRequest khachHang,
                              BindingResult bindingResult,
                              @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo) {

        if (pageNo < 0) {
            pageNo = 0;
        }
//        KhachHang checkMa = khachHangService.findByMa(khachHang.getMa());
//        if (checkMa != null) {
//            model.addAttribute("checkMa", "Mã đã tồn tại vui lòng nhập mã khác");
//            model.addAttribute("listKH", khachHangService.fillAll(0, 5));
//            model.addAttribute("chucVu", chucVuService.getAllChucVu());
//            return "/viewKhachHang/khach-hang";
//        }
         if (bindingResult.hasErrors()) {
            model.addAttribute("listKH", khachHangService.fillAll(0, 5));
            model.addAttribute("chucVu", chucVuService.getAllChucVu());
            return "/viewKhachHang/khach-hang";
        } else {
            khachHangService.addOrUpdate(khachHang);
            model.addAttribute("listKH", khachHangService.fillAll(pageNo, 5));
            return "redirect:/khach-hang";
        }
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") UUID id,
                         Model model) {
        khachHangService.delete(id);
        return "redirect:/khach-hang";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") UUID id,
                       @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model) {
        if (pageNo < 0) {
            pageNo = 0;
        }
        model.addAttribute("khachHang", khachHangService.getById(id));
        model.addAttribute("chucVu", chucVuService.getAllChucVu());
        model.addAttribute("listKH", khachHangService.fillAll(pageNo, 5));
        return "/viewKhachHang/khach-hang";
    }
}
