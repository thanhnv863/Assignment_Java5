package com.example.assignment_java5.controller;

import com.example.assignment_java5.enitityRequest.KhachHangRequest;
import com.example.assignment_java5.entity.CartItem;
import com.example.assignment_java5.entity.ChiTietSanPham;
import com.example.assignment_java5.entity.HoaDon;
import com.example.assignment_java5.entity.KhachHang;
import com.example.assignment_java5.service.CartItemService;
import com.example.assignment_java5.service.ChiTietSanPhamService;
import com.example.assignment_java5.service.DeGiayService;
import com.example.assignment_java5.service.DongSanPhamService;
import com.example.assignment_java5.service.GioHangChiTietService;
import com.example.assignment_java5.service.GioHangService;
import com.example.assignment_java5.service.HoaDonChiTietService;
import com.example.assignment_java5.service.HoaDonService;
import com.example.assignment_java5.service.KhachHangService;
import com.example.assignment_java5.service.MauSacService;
import com.example.assignment_java5.service.SanPhamService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class MuaHangController {
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

    @Autowired
    private GioHangService gioHangService;

    @Autowired
    private GioHangChiTietService gioHangChiTietService;

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private KhachHangService khachHangService;

    @GetMapping("/mua-hang/{id}")
    public String muaHang(@PathVariable("id") UUID id, Model model) {
        ChiTietSanPham ctsp = chiTietSanPhamService.getById(id);
        model.addAttribute("ctsp", ctsp);
        model.addAttribute("dongSP", dongSanPhamService.findAllByTen(ctsp.getSanPham().getTen()));
        model.addAttribute("deGiay", deGiayService.findAllByTen(ctsp.getSanPham().getTen()));
        model.addAttribute("mauSac", mauSacService.findAllByTen(ctsp.getSanPham().getTen()));
        model.addAttribute("ctspKichCo", chiTietSanPhamService.findAllByKichCo(ctsp.getSanPham().getTen(), ctsp.getMauSac().getTen()));
        return "viewMuaHang/mua-hang";
    }

//    @GetMapping("/mua-hang/{tenSP}/{tenMS}")
//    public String muaHangMuaSac(@PathVariable("tenSP") String tenSP, @PathVariable("tenMS") String mauSP, Model model) {
//        ChiTietSanPham ctsp = chiTietSanPhamService.getChiTietSanPhamBySanPhamTenAndMauSacTen(tenSP, mauSP);
//        model.addAttribute("ctsp", ctsp);
//        model.addAttribute("dongSP", dongSanPhamService.findAllByTen(ctsp.getSanPham().getTen()));
//        model.addAttribute("deGiay", deGiayService.findAllByTen(ctsp.getSanPham().getTen()));
//        model.addAttribute("mauSac", mauSacService.findAllByTen(ctsp.getSanPham().getTen()));
//        model.addAttribute("ctspKichCo", chiTietSanPhamService.findAllByKichCo(ctsp.getSanPham().getTen(), ctsp.getMauSac().getTen()));
//        return "viewMuaHang/mua-hang";
//    }

    @GetMapping("/mua-hang-dang-nhap/{id}")
    public String muaHangDangNhap(@PathVariable("id") UUID id, Model model) {
        ChiTietSanPham ctsp = chiTietSanPhamService.getById(id);
        model.addAttribute("ctsp", ctsp);
        model.addAttribute("dongSP", dongSanPhamService.findAllByTen(ctsp.getSanPham().getTen()));
        model.addAttribute("deGiay", deGiayService.findAllByTen(ctsp.getSanPham().getTen()));
        model.addAttribute("mauSac", mauSacService.findAllByTen(ctsp.getSanPham().getTen()));
        model.addAttribute("ctspKichCo", chiTietSanPhamService.findAllByKichCo(ctsp.getSanPham().getTen(), ctsp.getMauSac().getTen()));
        return "viewMuaHang/mua-hang-dang-nhap";
    }

    @GetMapping("/nguoi-dung-add-cart/{id}")
    public String nguoiDungAddCart(
            @PathVariable("id") UUID id,
            @RequestParam("soLuong") String soLuong,
            Model model,
            HttpServletRequest request

    ) {
        if (soLuong == null || soLuong.isEmpty()) {
            soLuong = "1";
        }
        ChiTietSanPham ctsp = chiTietSanPhamService.getById(id);
        if (Integer.valueOf(soLuong) > ctsp.getSoLuong()) {
            model.addAttribute("checkSoLuong", "Số lương vượt quá sản phẩm hiện có");
            model.addAttribute("ctsp", ctsp);
            model.addAttribute("dongSP", dongSanPhamService.findAllByTen(ctsp.getSanPham().getTen()));
            model.addAttribute("deGiay", deGiayService.findAllByTen(ctsp.getSanPham().getTen()));
            model.addAttribute("mauSac", mauSacService.findAllByTen(ctsp.getSanPham().getTen()));
            model.addAttribute("ctspKichCo", chiTietSanPhamService.findAllByKichCo(ctsp.getSanPham().getTen(), ctsp.getMauSac().getTen()));
            return "viewMuaHang/mua-hang-dang-nhap";
        } else {
            HttpSession session = request.getSession();
            KhachHang khachHang = (KhachHang) session.getAttribute("khachHang");

            gioHangService.saveGioHang(ctsp, khachHang, Integer.valueOf(soLuong));
            return "redirect:/trang-chu/khach-hang-dang-nhap";
        }


    }

    @GetMapping("/add-cart/{id}")
    public String addCart(
            @PathVariable("id") UUID id,
            @RequestParam("soLuong") String soLuong,
            Model model,
            HttpServletRequest request

    ) {
        if (soLuong == null || soLuong.isEmpty()) {
            soLuong = "1";
        }

        HttpSession session = request.getSession();
        ChiTietSanPham ctsp = chiTietSanPhamService.getById(id);
        if (Integer.valueOf(soLuong) > ctsp.getSoLuong()) {
            model.addAttribute("checkSoLuong", "Số lương vượt quá sản phẩm hiện có");
            model.addAttribute("ctsp", ctsp);
            model.addAttribute("dongSP", dongSanPhamService.findAllByTen(ctsp.getSanPham().getTen()));
            model.addAttribute("deGiay", deGiayService.findAllByTen(ctsp.getSanPham().getTen()));
            model.addAttribute("mauSac", mauSacService.findAllByTen(ctsp.getSanPham().getTen()));
            model.addAttribute("ctspKichCo", chiTietSanPhamService.findAllByKichCo(ctsp.getSanPham().getTen(), ctsp.getMauSac().getTen()));
            return "viewMuaHang/mua-hang";
        } else {
            CartItem newItem = new CartItem();
            newItem.setIdCTSP(ctsp.getId());
            newItem.setTenSP(ctsp.getSanPham().getTen());
            newItem.setAnh(ctsp.getAnh());
            newItem.setGia(ctsp.getDonGia());
            newItem.setSoLuong(Integer.valueOf(soLuong));

            CartItem cart = (CartItem) session.getAttribute("cart");
            if (cart == null) {
                cart = new CartItem();
                session.setAttribute("cart", cart);
            }
            cartItemService.add(newItem);
            return "redirect:/trang-chu/khach-hang";
        }

    }


    @GetMapping("/gio-hang-khach-hang")
    public String gioHang(Model model) {
        model.addAttribute("cart", cartItemService.items());
//        model.addAttribute("cart", gioHangChiTietService.getAll());
        return "viewMuaHang/gio-hang-khach-hang";
    }


    @GetMapping("/gio-hang-khach-hang/remove/{idCTSP}")
    public String removeGiohang(@PathVariable("idCTSP") UUID idCTSP, Model model) {
        cartItemService.delete(idCTSP);
        return "redirect:/gio-hang-khach-hang";
    }

    @GetMapping("/thanh-toan")
    public String thanhToan(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (cartItemService.items().isEmpty()) {
            model.addAttribute("cart", cartItemService.items());
            return "viewMuaHang/gio-hang-khach-hang";
        } else {
            model.addAttribute("cart", cartItemService.items());
            model.addAttribute("hoaDonKhachHang", new HoaDon());
            session.removeAttribute("cart");
            return "viewMuaHang/thanh-toan";
        }
    }


    @PostMapping("/hoan-tat")
    public String hoanTat(Model model, @ModelAttribute("hoaDonKhachHang") HoaDon hoaDonKhachHang) {
        model.addAttribute("cart", cartItemService.items());
        hoaDonService.saveHoaDon(hoaDonKhachHang);
        return "viewMuaHang/hoan-tat";
    }

    @GetMapping("/profile/khach-hang")
    public String profileKhachHang(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        KhachHang khachHang = (KhachHang) session.getAttribute("khachHang");
        model.addAttribute("profileKhachHang", khachHangService.getById(khachHang.getId()));
        return "viewMuaHang/profile-khach-hang";
    }

    @PostMapping("/profile/cap-nhat")
    public String capNhatProfile(Model model, @Validated @ModelAttribute("profileKhachHang") KhachHangRequest profileKhachHang,
                                 BindingResult bindingResult,
                                 HttpServletRequest request

    ) {
        if (bindingResult.hasErrors()) {
            return "viewMuaHang/profile-khach-hang";
        } else {
            khachHangService.updateProfile(profileKhachHang);
            HttpSession session = request.getSession();
            session.setAttribute("khachHang", khachHangService.getById(profileKhachHang.getId()));
            return "redirect:/profile/khach-hang";
        }

    }

    @GetMapping("/gio-hang-khach-hang-dang-nhap")
    public String gioHangDangNhap(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        KhachHang khachHang = (KhachHang) session.getAttribute("khachHang");
        model.addAttribute("cart", gioHangChiTietService.findGioHangChiTietByGioHangKhachHangId(khachHang.getId()));
        return "viewMuaHang/gio-hang-khach-hang-dang-nhap";
    }

    @GetMapping("/gio-hang-khach-hang-dang-nhap/remove/{idCTSP}")
    public String removeGiohangDangNhap(@PathVariable("idCTSP") UUID idCTSP, Model model) {
        gioHangChiTietService.deleteByChiTietSanPhamId(idCTSP);
        return "redirect:/gio-hang-khach-hang-dang-nhap";
    }


    @GetMapping("/thanh-toan-dang-nhap")
    public String thanhToanDN(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        KhachHang khachHang = (KhachHang) session.getAttribute("khachHang");

        if (gioHangChiTietService.findGioHangChiTietByGioHangKhachHangId(khachHang.getId()) == null) {
            model.addAttribute("cart", gioHangChiTietService.findGioHangChiTietByGioHangKhachHangId(khachHang.getId()));
            return "viewMuaHang/gio-hang-khach-hang-dang-nhap";
        } else {
            model.addAttribute("cart", gioHangChiTietService.findGioHangChiTietByGioHangKhachHangId(khachHang.getId()));
            model.addAttribute("khachHang", khachHangService.getById(khachHang.getId()));
            model.addAttribute("hoaDonKhachHang", new HoaDon());
            return "viewMuaHang/thanh-toan-dang-nhap";
        }
    }

    @PostMapping("/nguoi-dung/dat-hang")
    public String hoanTatNguoiDung(Model model, @ModelAttribute("hoaDonKhachHang") HoaDon hoaDonKhachHang, HttpServletRequest request) {
        HttpSession session = request.getSession();
        KhachHang khachHang = (KhachHang) session.getAttribute("khachHang");
        hoaDonService.saveHoaDonNguoiDung(hoaDonKhachHang, khachHang.getId());
        return "viewMuaHang/dat-hang-thanh-cong";
    }

    @GetMapping("/nguoi-dung/hoa-don")
    public String datHangThanhCong(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        HoaDon hoaDon = (HoaDon) session.getAttribute("hoaDonS");
        model.addAttribute("hoaDon", hoaDonService.getByMa(hoaDon.getMa()));
        model.addAttribute("cart", hoaDonChiTietService.getByHoaDonMa(hoaDon.getMa()));
        return "viewMuaHang/nguoi-dung-hoa-don";
    }

    @GetMapping("/lich-su-hoa-don")
    public String lichSuHoaDon(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        KhachHang khachHang = (KhachHang) session.getAttribute("khachHang");
        model.addAttribute("lichSuHoaDon", hoaDonChiTietService.findByHoaDonKhachHangId(khachHang.getId()));
        return "viewMuaHang/lich-su-hoa-don";
    }

    @GetMapping("/nguoi-dung/delete-all")
    public String nguoiDungDeleteAll() {
        gioHangChiTietService.deleteAll();
        gioHangService.deleteAll();
        return "redirect:/gio-hang-khach-hang-dang-nhap";
    }

}

