package com.example.assignment_java5.controller;

import com.example.assignment_java5.enitityRequest.KhachHangRequest;
import com.example.assignment_java5.entity.KhachHang;
import com.example.assignment_java5.service.ChiTietSanPhamService;
import com.example.assignment_java5.service.KhachHangService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("khachHang", new KhachHang());
        return "viewLogin/login-khach-hang";
    }

    @PostMapping("/login/sign-up")
    public String signUp(Model model, @ModelAttribute("khachHang") KhachHangRequest khachHang) {
        khachHangService.login(khachHang);
        return "redirect:/login";
    }

    @PostMapping("/login/sign-in")
    public String signIn(Model model, @RequestParam("email") String email,
                         @RequestParam("matKhau") String matKhau,
                         HttpServletRequest request) {
        if (email.equalsIgnoreCase("thanh@gmail.com") && matKhau.equalsIgnoreCase("123")){
            return "redirect:/trang-chu/admin";
        }else {
            KhachHang khachHang = khachHangService.findKhachHangByEmailAndMatKhau(email, matKhau);
            KhachHang newKhachHang = new KhachHang();
            newKhachHang.setId(khachHang.getId());
            newKhachHang.setEmail(khachHang.getEmail());
            newKhachHang.setTaiKhoan(khachHang.getTaiKhoan());
            newKhachHang.setMatKhau(khachHang.getMatKhau());
            newKhachHang.setHoTen(khachHang.getHoTen());
            newKhachHang.setSdt(khachHang.getSdt());


            HttpSession session = request.getSession();
            session.setAttribute("khachHang", newKhachHang);

            return khachHangService.loginKhachHang(email, matKhau);
        }
    }

    @GetMapping("/nguoi-dung-dang-xuat")
    public String ngDungDangXuat(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        model.addAttribute("listCTSP", chiTietSanPhamService.fillAll(pageNo, 6));
        return "viewTrangChu/trang-chu-khach-hang";
    }
//    @GetMapping("/profile")
//    public String profile(Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getName();

//        KhachHang user = khachHangRepo.findByEmail(email);
//        model.addAttribute("user", user);
//        return "profile";
//    }

//    @GetMapping("/")
//    public String home() {
//        return "viewMuaHang/dat-hang-thanh-cong";
//    }

//    @GetMapping("/")
//    public String trangChuKhachHang(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model) {
//        model.addAttribute("listCTSP", chiTietSanPhamService.fillAll(pageNo, 6));
//        return "viewTrangChu/trang-chu-khach-hang";
//    }

//    private AuthenticationManager authenticationManager;
//
//    public LoginController() {
//
//    }
//
//    public LoginController(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }
//
//
//    @GetMapping("/view-login")
//    public String login() {
//        return "giaodien/login";
//    }
//
//    @PostMapping("/login")
//    public String processLogin(@RequestParam("username") String username,
//                               @RequestParam("password") String password, RedirectAttributes redirectAttributes) {
//        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
//        try {
//            Authentication authentication = authenticationManager.authenticate(authRequest);
//
//            // Đặt thông tin xác thực vào SecurityContextHolder
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            System.out.println(authentication.getAuthorities());
////            System.out.println( SecurityContextHolder.getContext().getAuthentication().);
//            // Kiểm tra thông tin xác thực
//            if (authentication.isAuthenticated()) {
//                // Kiểm tra vai trò của người dùng và chuyển hướng tương ứng
//                if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
//                    return "redirect:/admin/dashboard";
//                } else {
//                    return "giaodien/trang-chu";
//                }
//            }
//        } catch (AuthenticationException ex) {
//            // Xử lý lỗi đăng nhập
//            redirectAttributes.addAttribute("error", "true");
//        }
//
//        return "redirect:/view-login?error=true";
//    }

}
