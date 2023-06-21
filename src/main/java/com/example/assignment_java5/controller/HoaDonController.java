package com.example.assignment_java5.controller;

import com.example.assignment_java5.enitityRequest.HoaDonRequest;
import com.example.assignment_java5.entity.HoaDon;
import com.example.assignment_java5.service.HoaDonService;
import com.example.assignment_java5.service.KhachHangService;
import com.example.assignment_java5.service.NhanVienService;
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
@RequestMapping("/hoa-don")
public class HoaDonController {
    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping()
    public String hienThi(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model) {
        if (pageNo < 0) {
            pageNo = 0;
        }
        model.addAttribute("nhanVien", nhanVienService.getAll());
        model.addAttribute("khachHang", khachHangService.getAll());
//        model.addAttribute("listHD", hoaDonService.fillAll(pageNo, 10));
        model.addAttribute("listHD", hoaDonService.findAllSortedByTrangThai(pageNo, 10));
        model.addAttribute("hoaDon", new HoaDon());
        return "/viewHoaDon/hoa-don";
    }

    @PostMapping("/addOrUpdate")
    public String addOrUpdate(Model model,
                              @Valid @ModelAttribute("hoaDon") HoaDonRequest hoaDon,
                              BindingResult bindingResult,
                              @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo) {

        if (pageNo < 0) {
            pageNo = 0;
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("nhanVien", nhanVienService.getAll());
            model.addAttribute("khachHang", khachHangService.getAll());
            model.addAttribute("listHD", hoaDonService.fillAll(pageNo, 5));
            return "/viewHoaDon/hoa-don";
        } else {
            hoaDonService.addOrUpdate(hoaDon);
            model.addAttribute("listHD", hoaDonService.fillAll(pageNo, 5));
            return "redirect:/hoa-don";
        }
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") UUID id,
                         Model model) {
        hoaDonService.delete(id);
        return "redirect:/hoa-don";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") UUID id,
                       @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model) {
        if (pageNo < 0) {
            pageNo = 0;
        }
        model.addAttribute("hoaDon", hoaDonService.getById(id));
        model.addAttribute("nhanVien", nhanVienService.getAll());
        model.addAttribute("khachHang", khachHangService.getAll());
        model.addAttribute("listHD", hoaDonService.fillAll(pageNo, 5));
        return "/viewHoaDon/hoa-don";
    }

}
