package com.example.assignment_java5.service.impl;

import com.example.assignment_java5.enitityRequest.HoaDonChiTietRequest;
import com.example.assignment_java5.entity.HoaDonChiTiet;
import com.example.assignment_java5.repository.HoaDonChiTietRepository;
import com.example.assignment_java5.service.HoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HoaDonChiTietServiceImpl implements HoaDonChiTietService {
    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;
    @Override
    public List<HoaDonChiTiet> getAll() {
        return hoaDonChiTietRepository.findAll();
    }

    @Override
    public Page<HoaDonChiTiet> fillAll(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return hoaDonChiTietRepository.findAll(pageable);
    }

    @Override
    public HoaDonChiTiet addOrUpdate(HoaDonChiTietRequest hoaDonChiTietRequest) {
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setId(hoaDonChiTietRequest.getId());
        hoaDonChiTiet.setChiTietSanPham(hoaDonChiTietRequest.getChiTietSanPham());
        hoaDonChiTiet.setHoaDon(hoaDonChiTietRequest.getHoaDon());
        hoaDonChiTiet.setSoLuong(hoaDonChiTietRequest.getSoLuong());
        hoaDonChiTiet.setDonGia(hoaDonChiTietRequest.getDonGia());

        return hoaDonChiTietRepository.save(hoaDonChiTiet);
    }

    @Override
    public void delete(UUID id) {
        hoaDonChiTietRepository.deleteById(id);
    }

    @Override
    public HoaDonChiTiet getById(UUID id) {
        return hoaDonChiTietRepository.getById(id);
    }

    @Override
    public HoaDonChiTiet saveHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet) {
        return hoaDonChiTietRepository.save(hoaDonChiTiet);
    }

    @Override
    public List<HoaDonChiTiet> getByHoaDonMa(String ma) {
        return hoaDonChiTietRepository.getByHoaDonMa(ma);
    }

    @Override
    public List<HoaDonChiTiet> findByHoaDonKhachHangId(UUID id) {
        return hoaDonChiTietRepository.findByHoaDonKhachHangId(id);
    }
}
