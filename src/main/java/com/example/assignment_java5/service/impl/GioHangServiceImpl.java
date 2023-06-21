package com.example.assignment_java5.service.impl;

import com.example.assignment_java5.enitityRequest.GioHangRequest;
import com.example.assignment_java5.entity.ChiTietSanPham;
import com.example.assignment_java5.entity.GioHang;
import com.example.assignment_java5.entity.GioHangChiTiet;
import com.example.assignment_java5.entity.KhachHang;
import com.example.assignment_java5.genMa.GenMa;
import com.example.assignment_java5.repository.ChiTietSanPhamRepository;
import com.example.assignment_java5.repository.GioHangChiTietRepository;
import com.example.assignment_java5.repository.GioHangRepository;
import com.example.assignment_java5.repository.KhachHangRepository;
import com.example.assignment_java5.service.GioHangService;
import com.example.assignment_java5.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class GioHangServiceImpl implements GioHangService {
    @Autowired
    private GioHangRepository gioHangRepository;

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;
    @Autowired
    private KhachHangRepository khachHangRepository;

    @Override
    public List<GioHang> getAll() {
        return gioHangRepository.findAll();
    }

    @Override
    public Page<GioHang> fillAll(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return gioHangRepository.findAll(pageable);
    }

    @Override
    public GioHang addOrUpdate(GioHangRequest gioHangRequest) {
        GioHang gioHang = new GioHang();
        gioHang.setId(gioHangRequest.getId());
        gioHang.setMa(new GenMa().maGen());
        gioHang.setKhachHang(gioHangRequest.getKhachHang());
        gioHang.setNhanVien(gioHangRequest.getNhanVien());
        gioHang.setNgayTao(gioHangRequest.getNgayTao());
        gioHang.setNgayThanhToan(gioHangRequest.getNgayThanhToan());
        gioHang.setTenNguoiNhan(gioHangRequest.getTenNguoiNhan());
        gioHang.setDiaChi(gioHangRequest.getDiaChi());
        gioHang.setSdt(gioHangRequest.getSdt());
        gioHang.setEmail(gioHangRequest.getEmail());
        gioHang.setHinhThucThanhToan(gioHangRequest.getHinhThucThanhToan());
        gioHang.setTrangThai(gioHangRequest.getTrangThai());
        return gioHangRepository.save(gioHang);
    }

    @Override
    public void delete(UUID id) {
        gioHangRepository.deleteById(id);
    }

    @Override
    public GioHang getById(UUID id) {
        return gioHangRepository.getById(id);
    }

    @Override
    public void saveGioHang(ChiTietSanPham chiTietSanPham, KhachHang khachHang, Integer soLuong) {
        String maGH = generateInvoiceNumberGH();
        GioHang gioHangKhachHang = gioHangRepository.findGioHangByKhachHang(khachHang.getId());
        if (gioHangKhachHang != null) {
            List<GioHangChiTiet> check = gioHangChiTietRepository.findGioHangChiTietByGioHangId(gioHangKhachHang.getId());
            if (check == null || check.isEmpty()) {
                gioHangRepository.deleteById(gioHangKhachHang.getId());
                gioHangKhachHang = null;
            }
        }

        if (gioHangKhachHang == null) {
            GioHang gioHang = new GioHang();
            LocalDate currentDate = LocalDate.now();
            gioHang.setKhachHang(khachHang);
            gioHang.setMa(maGH);
            gioHang.setNgayTao(Date.valueOf(currentDate));
            gioHang.setTrangThai(0);
            gioHangRepository.save(gioHang);
            GioHang gioHangX = getByMa(maGH);
            GioHangChiTiet gioHangChiTiet = new GioHangChiTiet(null, gioHangX, chiTietSanPham, Integer.valueOf(soLuong), chiTietSanPham.getDonGia());
            gioHangChiTietRepository.save(gioHangChiTiet);
        } else {
            GioHang gioHang = new GioHang();
            gioHang.setId(gioHangKhachHang.getId());
            gioHang.setKhachHang(gioHangKhachHang.getKhachHang());
            gioHang.setMa(gioHangKhachHang.getMa());
            gioHang.setNgayTao(gioHangKhachHang.getNgayTao());
            gioHang.setTrangThai(gioHangKhachHang.getTrangThai());
            gioHangRepository.save(gioHang);
            GioHang gioHangX = getByMa(gioHangKhachHang.getMa());
            GioHangChiTiet gioHangChiTietx = gioHangChiTietRepository.findGioHangChiTietByGioHangIdAndAndChiTietSanPhamId(gioHangX.getId(), chiTietSanPham.getId());
            if (gioHangChiTietx == null) {
                for (GioHangChiTiet z : gioHangChiTietRepository.findGioHangChiTietByGioHangId(gioHangX.getId())) {
                    GioHangChiTiet gioHangChiTiet = new GioHangChiTiet(null, gioHangX, chiTietSanPham, soLuong, chiTietSanPham.getDonGia());
                    gioHangChiTietRepository.save(gioHangChiTiet);
                }
            } else {
                for (GioHangChiTiet z : gioHangChiTietRepository.findGioHangChiTietById(gioHangChiTietx.getId())) {
                    if (z.getChiTietSanPham().getId().equals(chiTietSanPham.getId())) {
                        gioHangChiTietRepository.updateSoLuongGHCT(z.getSoLuong() + soLuong, gioHangChiTietx.getId());
                    }
                }
            }
        }
    }
//    }

    @Override
    public GioHang getByMa(String ma) {
        return gioHangRepository.getByMa(ma);
    }

    @Override
    public GioHang findGioHangByKhachHang(UUID idKH) {
        return gioHangRepository.findGioHangByKhachHang(idKH);
    }

    @Override
    public GioHang findByMa(String ma) {
        return gioHangRepository.findByMa(ma);
    }

    @Override
    public void deleteAll() {
        gioHangRepository.deleteAll();
    }

    public String generateInvoiceNumberGH() {
        String prefix = "GH";
        int randomNumber = new Random().nextInt(10000); // Số ngẫu nhiên từ 0 đến 9999
        String invoiceNumber = prefix + randomNumber;
        return invoiceNumber;
    }
}
