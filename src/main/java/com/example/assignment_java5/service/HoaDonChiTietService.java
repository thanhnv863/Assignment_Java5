package com.example.assignment_java5.service;

import com.example.assignment_java5.enitityRequest.HoaDonChiTietRequest;
import com.example.assignment_java5.entity.HoaDon;
import com.example.assignment_java5.entity.HoaDonChiTiet;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface HoaDonChiTietService {
    List<HoaDonChiTiet> getAll();

    Page<HoaDonChiTiet> fillAll(Integer pageNo, Integer size);

    HoaDonChiTiet addOrUpdate(HoaDonChiTietRequest hoaDonChiTietRequest);

    void delete(UUID id);

    HoaDonChiTiet getById(UUID id);

    HoaDonChiTiet saveHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet);

    List<HoaDonChiTiet> getByHoaDonMa(String ma);

    List<HoaDonChiTiet> findByHoaDonKhachHangId(UUID id);

}
