package com.example.assignment_java5.controller;

import com.example.assignment_java5.enitityRequest.ChiTietSanPhamRequest;
import com.example.assignment_java5.entity.ChiTietSanPham;
import com.example.assignment_java5.service.ChiTietSanPhamService;
import com.example.assignment_java5.service.DeGiayService;
import com.example.assignment_java5.service.DongSanPhamService;
import com.example.assignment_java5.service.MauSacService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/chi-tiet-san-pham")
public class ChiTietSanPhamController {
    ModelAndView modelAndView = new ModelAndView();
    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private DongSanPhamService dongSanPhamService;

    @Autowired
    private DeGiayService deGiayService;

    @Autowired
    private MauSacService mauSacService;


    @GetMapping()
    public String hienThi(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model) {
        if (pageNo < 0) {
            pageNo = 0;
        }
        model.addAttribute("listCTSP", chiTietSanPhamService.fillAll(pageNo, 5));
        model.addAttribute("sanPhamx", sanPhamService.getAll());
        model.addAttribute("dongSPx", dongSanPhamService.getAll());
        model.addAttribute("deGiayx", deGiayService.getAll());
        model.addAttribute("mauSacx", mauSacService.getAll());
        model.addAttribute("chiTietSanPham", new ChiTietSanPham());
        return "/viewChiTietSanPham/chi-tiet-san-pham";
    }


    @PostMapping("/addOrUpdate")
    public String addOrUpdate(
            Model model,
            @RequestParam("anh") MultipartFile file,
            @Valid @ModelAttribute("chiTietSanPham") ChiTietSanPhamRequest chiTietSanPham,
            BindingResult bindingResult,
            @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo
    ) throws IOException {
        if (pageNo < 0) {
            pageNo = 0;
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("sanPhamx", sanPhamService.getAll());
            model.addAttribute("dongSPx", dongSanPhamService.getAll());
            model.addAttribute("deGiayx", deGiayService.getAll());
            model.addAttribute("mauSacx", mauSacService.getAll());
            model.addAttribute("listCTSP", chiTietSanPhamService.fillAll(0, 5));
            System.out.println(file);
            System.out.println(bindingResult);
            return ("viewChiTietSanPham/chi-tiet-san-pham");
        } else {
            chiTietSanPhamService.addOrUpdate(chiTietSanPham,file);
            model.addAttribute("listCTSP", chiTietSanPhamService.fillAll(pageNo, 5));
            return "redirect:/chi-tiet-san-pham";
        }
//        chiTietSanPhamService.addOrUpdate(chiTietSanPham,file);
//        model.addAttribute("listCTSP", chiTietSanPhamService.fillAll(pageNo, 5));
//        return "redirect:/chi-tiet-san-pham";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") UUID id,
                         Model model) {
        chiTietSanPhamService.delete(id);
        return "redirect:/chi-tiet-san-pham";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") UUID id,
                       @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model) {
        if (pageNo < 0) {
            pageNo = 0;
        }
        model.addAttribute("chiTietSanPham", chiTietSanPhamService.getById(id));
        model.addAttribute("sanPhamx", sanPhamService.getAll());
        model.addAttribute("dongSPx", dongSanPhamService.getAll());
        model.addAttribute("deGiayx", deGiayService.getAll());
        model.addAttribute("mauSacx", mauSacService.getAll());
        model.addAttribute("listCTSP", chiTietSanPhamService.fillAll(pageNo, 5));
        return "/viewChiTietSanPham/chi-tiet-san-pham";
    }
}
