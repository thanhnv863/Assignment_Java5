package com.example.assignment_java5.service.impl;

import com.example.assignment_java5.enitityRequest.GioHangChiTietRequest;
import com.example.assignment_java5.enitityRequest.HoaDonChiTietRequest;
import com.example.assignment_java5.entity.GioHangChiTiet;
import com.example.assignment_java5.entity.HoaDonChiTiet;
import com.example.assignment_java5.repository.GioHangChiTietRepository;
import com.example.assignment_java5.repository.HoaDonChiTietRepository;
import com.example.assignment_java5.service.GioHangChiTietService;
import com.example.assignment_java5.service.HoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GioHangChiTietServiceImpl implements GioHangChiTietService {
    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;
    @Override
    public List<GioHangChiTiet> getAll() {
        return gioHangChiTietRepository.findAll();
    }

    @Override
    public Page<GioHangChiTiet> fillAll(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return gioHangChiTietRepository.findAll(pageable);
    }

    @Override
    public GioHangChiTiet addOrUpdate(GioHangChiTietRequest gioHangChiTietRequest) {
        GioHangChiTiet gioHangChiTiet = new GioHangChiTiet();
        gioHangChiTiet.setId(gioHangChiTietRequest.getId());
        gioHangChiTiet.setChiTietSanPham(gioHangChiTietRequest.getChiTietSanPham());
        gioHangChiTiet.setGioHang(gioHangChiTietRequest.getGioHang());
        gioHangChiTiet.setSoLuong(gioHangChiTietRequest.getSoLuong());
        gioHangChiTiet.setDonGia(gioHangChiTietRequest.getDonGia());

        return gioHangChiTietRepository.save(gioHangChiTiet);
    }

    @Override
    public void delete(UUID id) {
        gioHangChiTietRepository.deleteById(id);
    }

    @Override
    public GioHangChiTiet getById(UUID id) {
        return gioHangChiTietRepository.getById(id);
    }

    @Override
    public GioHangChiTiet saveGioHangChiTiet(GioHangChiTiet gioHangChiTiet) {
        return gioHangChiTietRepository.save(gioHangChiTiet);
    }

    @Override
    public List<GioHangChiTiet> findGioHangChiTietByGioHangKhachHangId(UUID id) {
        return gioHangChiTietRepository.findGioHangChiTietByGioHangKhachHangId(id);
    }

    @Override
    public void deleteByChiTietSanPhamId(UUID id) {
        gioHangChiTietRepository.deleteGioHangChiTietByChiTietSanPhamId(id);
    }

    @Override
    public void deleteAll() {
        gioHangChiTietRepository.deleteAll();
    }

//    @Override
//    public void updateSoLuongGHCT(Integer soLuongMoi, UUID idGH) {
//        gioHangChiTietRepository.updateSoLuongGHCT(soLuongMoi, idGH);
//    }

}
