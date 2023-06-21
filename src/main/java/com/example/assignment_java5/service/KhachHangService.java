package com.example.assignment_java5.service;

import com.example.assignment_java5.enitityRequest.KhachHangRequest;
import com.example.assignment_java5.entity.KhachHang;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface KhachHangService {
    List<KhachHang> getAll();

    Page<KhachHang> fillAll(Integer pageNo, Integer size);

    KhachHang addOrUpdate(KhachHangRequest khachHangRequest);

    void delete(UUID id);

    KhachHang getById(UUID id);

    KhachHang findKhachHangByEmailAndMatKhau(String email, String matKhau);

    String loginKhachHang(String email, String matKhau);

    KhachHang login(KhachHangRequest khachHang);

    KhachHang updateProfile(KhachHangRequest khachHang);

    KhachHang findByMa(String ma);

}
