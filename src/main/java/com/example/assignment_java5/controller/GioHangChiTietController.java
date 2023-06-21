package com.example.assignment_java5.controller;

import com.example.assignment_java5.enitityRequest.GioHangChiTietRequest;
import com.example.assignment_java5.entity.GioHangChiTiet;
import com.example.assignment_java5.service.ChiTietSanPhamService;
import com.example.assignment_java5.service.GioHangChiTietService;
import com.example.assignment_java5.service.GioHangService;
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
@RequestMapping("/gio-hang-chi-tiet")
public class GioHangChiTietController {

    @Autowired
    private GioHangChiTietService gioHangChiTietService;

    @Autowired
    private GioHangService gioHangService;

    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    @GetMapping()
    public String hienThi(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model) {
        if (pageNo < 0) {
            pageNo = 0;
        }
        model.addAttribute("gioHang", gioHangService.getAll());
        model.addAttribute("chiTietSanPham", chiTietSanPhamService.getAll());
        model.addAttribute("listGHCT", gioHangChiTietService.fillAll(pageNo, 5));
        model.addAttribute("gioHangChiTiet", new GioHangChiTiet());
        return "/viewGioHangChiTiet/gio-hang-chi-tiet";
    }

    @PostMapping("/addOrUpdate")
    public String addOrUpdate(Model model,
                              @Valid @ModelAttribute("gioHangChiTiet") GioHangChiTietRequest gioHangChiTiet,
                              BindingResult bindingResult,
                              @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo) {

        if (pageNo < 0) {
            pageNo = 0;
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("gioHang", gioHangService.getAll());
            model.addAttribute("chiTietSanPham", chiTietSanPhamService.getAll());
            model.addAttribute("listGHCT", gioHangChiTietService.fillAll(pageNo, 5));
            return "/viewGioHangChiTiet/gio-hang-chi-tiet";
        } else {
            gioHangChiTietService.addOrUpdate(gioHangChiTiet);
            model.addAttribute("listGHCT", gioHangChiTietService.fillAll(pageNo, 5));
            return "redirect:/gio-hang-chi-tiet";
        }
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") UUID id,
                         Model model) {
        gioHangChiTietService.delete(id);
        return "redirect:/gio-hang-chi-tiet";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") UUID id,
                       @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model) {
        if (pageNo < 0) {
            pageNo = 0;
        }
        model.addAttribute("gioHangChiTiet", gioHangChiTietService.getById(id));
        model.addAttribute("gioHang", gioHangService.getAll());
        model.addAttribute("chiTietSanPham", chiTietSanPhamService.getAll());
        model.addAttribute("listGHCT", gioHangChiTietService.fillAll(pageNo, 5));
        return "/viewGioHangChiTiet/gio-hang-chi-tiet";
    }
}
