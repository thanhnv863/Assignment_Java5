package com.example.assignment_java5.controller;

import com.example.assignment_java5.service.ChiTietSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/trang-chu")
public class TrangChuController {

    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    @GetMapping("/khach-hang")
    public String trangChuKhachHang(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model) {
        model.addAttribute("listCTSP", chiTietSanPhamService.fillAll(pageNo, 6));
        return "viewTrangChu/trang-chu-khach-hang";
    }

    @GetMapping("/khach-hang-dang-nhap")
    public String trangChuKhachHangDN(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model) {
        model.addAttribute("listCTSP", chiTietSanPhamService.fillAll(pageNo, 6));
        return "viewTrangChu/trang-chu-khach-hang-dang-nhap";
    }

    @GetMapping("/admin")
    public String trangChuAdmin() {
        return "viewTrangChu/trang-chu-admin";
    }

}
