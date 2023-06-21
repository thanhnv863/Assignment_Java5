package com.example.assignment_java5.controller;

import com.example.assignment_java5.enitityRequest.ChucVuRequest;
import com.example.assignment_java5.entity.ChucVu;
import com.example.assignment_java5.service.ChucVuService;
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
@RequestMapping("/chuc-vu")
public class ChucVuController {

    @Autowired
    private ChucVuService chucVuService;

    @GetMapping()
    public String hienThiChucVu(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model) {
        if (pageNo < 0) {
            pageNo = 0;
        }
        model.addAttribute("listCV", chucVuService.page(pageNo, 5));
        model.addAttribute("chucVu", new ChucVu());
        return "/viewChucVu/chuc-vu";
    }

    @PostMapping("/addOrUpdate")
    public String addOrUpdate(Model model,
                              @Valid @ModelAttribute("chucVu") ChucVuRequest chucVu,
                              BindingResult bindingResult,
                              @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo) {

        if (pageNo < 0) {
            pageNo = 0;
        }
//        ChucVu checkMa = chucVuService.findByMa(chucVu.getMa());
//        if (checkMa != null) {
//            model.addAttribute("checkMa", "Mã đã tồn tại vui lòng nhập mã khác");
//            model.addAttribute("listCV", chucVuService.page(0, 5));
//            return "/viewChucVu/chuc-vu";
//        }
         if (bindingResult.hasErrors()) {
            model.addAttribute("listCV", chucVuService.page(0, 5));
            return "/viewChucVu/chuc-vu";
        } else {
            chucVuService.addOrUpdate(chucVu);
            model.addAttribute("listCV", chucVuService.page(pageNo, 5));
            return "redirect:/chuc-vu";
        }
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") UUID id,
                         Model model) {
        chucVuService.delete(id);
        return "redirect:/chuc-vu";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") UUID id,
                       @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo, Model model) {
        if (pageNo < 0) {
            pageNo = 0;
        }
        model.addAttribute("chucVu", chucVuService.getById(id));
        model.addAttribute("listCV", chucVuService.page(pageNo, 5));
        return "/viewChucVu/chuc-vu";
    }
}
