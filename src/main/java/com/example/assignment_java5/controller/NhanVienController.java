package com.example.assignment_java5.controller;

import com.example.assignment_java5.enitityRequest.NhanVienRequest;
import com.example.assignment_java5.entity.NhanVien;
import com.example.assignment_java5.service.ChucVuService;
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
@RequestMapping("/nhan-vien")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private ChucVuService chucVuService;

    @GetMapping()
    public String hienThi(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model) {
        if (pageNo < 0) {
            pageNo = 0;
        }
        model.addAttribute("listNV", nhanVienService.fillAll(pageNo, 5));
        model.addAttribute("chucVu", chucVuService.getAllChucVu());
        model.addAttribute("nhanVien", new NhanVien());
        return "/viewNhanVien/nhan-vien";
    }

    @PostMapping("/addOrUpdate")
    public String addOrUpdate(Model model,
                              @Valid @ModelAttribute("nhanVien") NhanVienRequest nhanVien,
                              BindingResult bindingResult,
                              @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo) {

        if (pageNo < 0) {
            pageNo = 0;
        }
//        NhanVien checkMa = nhanVienService.findByMa(nhanVien.getMa());
//        if (checkMa != null) {
//            model.addAttribute("checkMa", "Mã đã tồn tại vui lòng nhập mã khác");
//            model.addAttribute("chucVu", chucVuService.getAllChucVu());
//            model.addAttribute("listNV", nhanVienService.fillAll(0, 5));
//            return "/viewNhanVien/nhan-vien";
//        }
         if (bindingResult.hasErrors()) {
            model.addAttribute("chucVu", chucVuService.getAllChucVu());
            model.addAttribute("listNV", nhanVienService.fillAll(0, 5));
            return "/viewNhanVien/nhan-vien";
        } else {
            nhanVienService.addOrUpdate(nhanVien);
            model.addAttribute("listNV", nhanVienService.fillAll(pageNo, 5));
            return "redirect:/nhan-vien";
        }
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") UUID id,
                         Model model) {
        nhanVienService.delete(id);
        return "redirect:/nhan-vien";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") UUID id,
                       @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model) {
        if (pageNo < 0) {
            pageNo = 0;
        }
        model.addAttribute("nhanVien", nhanVienService.getById(id));
        model.addAttribute("chucVu", chucVuService.getAllChucVu());
        model.addAttribute("listNV", nhanVienService.fillAll(pageNo, 5));
        return "/viewNhanVien/nhan-vien";
    }
}
