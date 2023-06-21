package com.example.assignment_java5.controller;

import com.example.assignment_java5.enitityRequest.GioHangRequest;
import com.example.assignment_java5.entity.GioHang;
import com.example.assignment_java5.service.GioHangService;
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
@RequestMapping("/gio-hang")
public class GioHangController {


    @Autowired
    private GioHangService gioHangService;

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
        model.addAttribute("listGH", gioHangService.fillAll(pageNo, 5));
        model.addAttribute("gioHang", new GioHang());
        return "/viewGioHang/gio-hang";
    }

    @PostMapping("/addOrUpdate")
    public String addOrUpdate(Model model,
                              @Valid @ModelAttribute("gioHang") GioHangRequest gioHang,
                              BindingResult bindingResult,
                              @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo) {

        if (pageNo < 0) {
            pageNo = 0;
        }
//        GioHang checkMa = gioHangService.findByMa(gioHang.getMa());
//        if (checkMa != null) {
//            model.addAttribute("checkMa", "Mã đã tồn tại vui lòng nhập mã khác");
//            model.addAttribute("nhanVien", nhanVienService.getAll());
//            model.addAttribute("khachHang", khachHangService.getAll());
//            model.addAttribute("listGH", gioHangService.fillAll(pageNo, 5));
//            return "/viewGioHang/gio-hang";
//        } else
            if (bindingResult.hasErrors()) {
            model.addAttribute("nhanVien", nhanVienService.getAll());
            model.addAttribute("khachHang", khachHangService.getAll());
            model.addAttribute("listGH", gioHangService.fillAll(pageNo, 5));
            return "/viewGioHang/gio-hang";
        } else {
            gioHangService.addOrUpdate(gioHang);
            model.addAttribute("listGH", gioHangService.fillAll(pageNo, 5));
            return "redirect:/gio-hang";
        }
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") UUID id,
                         Model model) {
        gioHangService.delete(id);
        return "redirect:/gio-hang";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") UUID id,
                       @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model) {
        if (pageNo < 0) {
            pageNo = 0;
        }
        model.addAttribute("gioHang", gioHangService.getById(id));
        model.addAttribute("nhanVien", nhanVienService.getAll());
        model.addAttribute("khachHang", khachHangService.getAll());
        model.addAttribute("listGH", gioHangService.fillAll(pageNo, 5));
        return "/viewGioHang/gio-hang";
    }

}
