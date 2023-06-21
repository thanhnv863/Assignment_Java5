package com.example.assignment_java5.service.impl;

import com.example.assignment_java5.enitityRequest.KhachHangRequest;
import com.example.assignment_java5.entity.KhachHang;
import com.example.assignment_java5.genMa.GenMa;
import com.example.assignment_java5.repository.KhachHangRepository;
import com.example.assignment_java5.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class KhachHangServiceImpl implements KhachHangService {
    @Autowired
    private KhachHangRepository khachHangRepository;

    @Override
    public List<KhachHang> getAll() {
        return khachHangRepository.findAll();
    }

    @Override
    public Page<KhachHang> fillAll(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return khachHangRepository.findAll(pageable);
    }


    @Override
    public KhachHang addOrUpdate(KhachHangRequest khachHangRequest) {
        KhachHang khachHang = new KhachHang();
        khachHang.setId(khachHangRequest.getId());
        khachHang.setMa(new GenMa().maGen());
        khachHang.setHoTen(khachHangRequest.getHoTen());
        khachHang.setSdt(khachHangRequest.getSdt());
        khachHang.setEmail(khachHangRequest.getEmail());
        khachHang.setNgaySinh(khachHangRequest.getNgaySinh());
        khachHang.setDiaChi(khachHangRequest.getDiaChi());
        khachHang.setTrangThai(khachHangRequest.getTrangThai());
        khachHang.setTaiKhoan(khachHangRequest.getTaiKhoan());
        khachHang.setMatKhau(khachHangRequest.getMatKhau());
        khachHang.setChucVu(khachHangRequest.getChucVu());
        return khachHangRepository.save(khachHang);
    }

    @Override
    public void delete(UUID id) {
        khachHangRepository.deleteById(id);
    }

    @Override
    public KhachHang getById(UUID id) {
        return khachHangRepository.getById(id);
    }

    @Override
    public KhachHang findKhachHangByEmailAndMatKhau(String email, String matKhau) {
        return khachHangRepository.findKhachHangByEmailAndMatKhau(email, matKhau);
    }

    @Override
    public String loginKhachHang(String email, String matKhau) {
        KhachHang khachHang = khachHangRepository.findKhachHangByEmailAndMatKhau(email, matKhau);
        if (khachHang == null) {
            return "viewLogin/login-khach-hang";
        } else if (email.equalsIgnoreCase(khachHang.getEmail()) && matKhau.equalsIgnoreCase(khachHang.getMatKhau())) {
            return "redirect:/trang-chu/khach-hang-dang-nhap";
        } else {
            return "viewLogin/login-khach-hang";
        }
    }

    @Override
    public KhachHang login(KhachHangRequest khachHangRequest) {
        KhachHang khachHang = new KhachHang();
        khachHang.setId(khachHangRequest.getId());
        khachHang.setMa(generateInvoiceNumberKH());
        khachHang.setHoTen(khachHangRequest.getHoTen());
        khachHang.setEmail(khachHangRequest.getEmail());
        khachHang.setMatKhau(khachHangRequest.getMatKhau());
        khachHang.setTaiKhoan(khachHangRequest.getTaiKhoan());
        khachHang.setTrangThai(1);
        return khachHangRepository.save(khachHang);
    }

    @Override
    public KhachHang updateProfile(KhachHangRequest khachHangRequest) {
        KhachHang khachHang = new KhachHang();
        khachHang.setId(khachHangRequest.getId());
        khachHang.setMa(khachHangRequest.getMa());
        khachHang.setHoTen(khachHangRequest.getHoTen());
        khachHang.setNgaySinh(khachHangRequest.getNgaySinh());
        khachHang.setDiaChi(khachHangRequest.getDiaChi());
        khachHang.setEmail(khachHangRequest.getEmail());
        khachHang.setMatKhau(khachHangRequest.getMatKhau());
        khachHang.setTaiKhoan(khachHangRequest.getTaiKhoan());
        khachHang.setTrangThai(1);
        khachHang.setSdt(khachHangRequest.getSdt());
        return khachHangRepository.save(khachHang);
    }

    @Override
    public KhachHang findByMa(String ma) {
        return khachHangRepository.findByMa(ma);
    }

    public String generateInvoiceNumberKH() {
        String prefix = "KH";
        int randomNumber = new Random().nextInt(90000); // Số ngẫu nhiên từ 0 đến 9999
        String invoiceNumber = prefix + randomNumber;
        return invoiceNumber;
    }
}
