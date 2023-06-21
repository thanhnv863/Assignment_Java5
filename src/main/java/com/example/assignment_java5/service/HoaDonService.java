package com.example.assignment_java5.service;


import com.example.assignment_java5.enitityRequest.HoaDonRequest;
import com.example.assignment_java5.entity.HoaDon;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface HoaDonService {

    List<HoaDon> getAll();

    Page<HoaDon> fillAll(Integer pageNo, Integer size);

    HoaDon addOrUpdate(HoaDonRequest hoaDonRequest);

    void delete(UUID id);

    HoaDon getById(UUID id);

    HoaDon getByMa(String ma);

    void saveHoaDon(HoaDon hoaDon);

    void saveHoaDonNguoiDung(HoaDon hoaDon, UUID idKH);

    HoaDon getByKhachHangId(UUID id);

    Page<HoaDon> findAllSortedByTrangThai(Integer pageNo, Integer pageSize);

    HoaDon findByMa(String ma);

}
