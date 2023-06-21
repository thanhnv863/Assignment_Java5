package com.example.assignment_java5.service;

import com.example.assignment_java5.enitityRequest.GioHangChiTietRequest;
import com.example.assignment_java5.entity.GioHangChiTiet;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface GioHangChiTietService {
    List<GioHangChiTiet> getAll();

    Page<GioHangChiTiet> fillAll(Integer pageNo, Integer size);

    GioHangChiTiet addOrUpdate(GioHangChiTietRequest gioHangChiTietRequest);

    void delete(UUID id);

    GioHangChiTiet getById(UUID id);

    GioHangChiTiet saveGioHangChiTiet(GioHangChiTiet gioHangChiTiet);

    List<GioHangChiTiet> findGioHangChiTietByGioHangKhachHangId(UUID id);

    void deleteByChiTietSanPhamId(UUID id);

//    void updateSoLuongGHCT(Integer soLuongMoi, UUID idGH);

    void deleteAll();


}
