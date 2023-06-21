package com.example.assignment_java5.controller;

import com.example.assignment_java5.enitityRequest.GioHangChiTietRequest;
import com.example.assignment_java5.enitityRequest.HoaDonChiTietRequest;
import com.example.assignment_java5.entity.GioHangChiTiet;
import com.example.assignment_java5.entity.HoaDonChiTiet;
import com.example.assignment_java5.service.ChiTietSanPhamService;
import com.example.assignment_java5.service.GioHangChiTietService;
import com.example.assignment_java5.service.HoaDonChiTietService;
import com.example.assignment_java5.service.HoaDonService;
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
@RequestMapping("/hoa-don-chi-tiet")
public class HoaDonChiTietController {
    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    @GetMapping()
    public String hienThi(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model) {
        if (pageNo < 0) {
            pageNo = 0;
        }
        model.addAttribute("hoaDon", hoaDonService.getAll());
        model.addAttribute("chiTietSanPham", chiTietSanPhamService.getAll());
        model.addAttribute("listHDCT", hoaDonChiTietService.fillAll(pageNo, 5));
        model.addAttribute("hoaDonChiTiet", new HoaDonChiTiet());
        return "/viewHoaDonChiTiet/hoa-don-chi-tiet";
    }

    @PostMapping("/addOrUpdate")
    public String addOrUpdate(Model model,
                              @Valid @ModelAttribute("hoaDonChiTiet") HoaDonChiTietRequest hoaDonChiTiet,
                              BindingResult bindingResult,
                              @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo) {

        if (pageNo < 0) {
            pageNo = 0;
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("hoaDon", hoaDonService.getAll());
            model.addAttribute("chiTietSanPham", chiTietSanPhamService.getAll());
            model.addAttribute("listHDCT", hoaDonChiTietService.fillAll(pageNo, 5));
            return "/viewHoaDonChiTiet/hoa-don-chi-tiet";
        } else {
            hoaDonChiTietService.addOrUpdate(hoaDonChiTiet);
            model.addAttribute("listHDCT", hoaDonChiTietService.fillAll(pageNo, 5));
            return "redirect:/hoa-don-chi-tiet";
        }
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") UUID id,
                         Model model) {
        hoaDonChiTietService.delete(id);
        return "redirect:/hoa-don-chi-tiet";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") UUID id,
                       @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model) {
        if (pageNo < 0) {
            pageNo = 0;
        }
        model.addAttribute("hoaDonChiTiet", hoaDonChiTietService.getById(id));
        model.addAttribute("hoaDon", hoaDonService.getAll());
        model.addAttribute("chiTietSanPham", chiTietSanPhamService.getAll());
        model.addAttribute("listHDCT", hoaDonChiTietService.fillAll(pageNo, 5));
        return "/viewHoaDonChiTiet/hoa-don-chi-tiet";
    }

}
