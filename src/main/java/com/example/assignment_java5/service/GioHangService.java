package com.example.assignment_java5.service;


import com.example.assignment_java5.enitityRequest.GioHangRequest;
import com.example.assignment_java5.entity.ChiTietSanPham;
import com.example.assignment_java5.entity.GioHang;
import com.example.assignment_java5.entity.KhachHang;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface GioHangService {

    List<GioHang> getAll();

    Page<GioHang> fillAll(Integer pageNo, Integer size);

    GioHang addOrUpdate(GioHangRequest gioHangRequest);

    void delete(UUID id);

    GioHang getById(UUID id);

    void saveGioHang(ChiTietSanPham ctsp, KhachHang khachHang, Integer soLuong);

    GioHang getByMa(String ma);

    GioHang findGioHangByKhachHang(UUID idKH);

    GioHang findByMa(String ma);

    void deleteAll();

}
