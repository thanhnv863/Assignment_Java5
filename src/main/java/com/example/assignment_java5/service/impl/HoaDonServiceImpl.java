package com.example.assignment_java5.service.impl;

import com.example.assignment_java5.enitityRequest.HoaDonRequest;
import com.example.assignment_java5.entity.CartItem;
import com.example.assignment_java5.entity.ChiTietSanPham;
import com.example.assignment_java5.entity.GioHang;
import com.example.assignment_java5.entity.GioHangChiTiet;
import com.example.assignment_java5.entity.HoaDon;
import com.example.assignment_java5.entity.HoaDonChiTiet;
import com.example.assignment_java5.entity.KhachHang;
import com.example.assignment_java5.genMa.GenMa;
import com.example.assignment_java5.repository.ChiTietSanPhamRepository;
import com.example.assignment_java5.repository.GioHangChiTietRepository;
import com.example.assignment_java5.repository.GioHangRepository;
import com.example.assignment_java5.repository.HoaDonChiTietRepository;
import com.example.assignment_java5.repository.HoaDonRepository;
import com.example.assignment_java5.repository.KhachHangRepository;
import com.example.assignment_java5.service.CartItemService;
import com.example.assignment_java5.service.ChiTietSanPhamService;
import com.example.assignment_java5.service.HoaDonChiTietService;
import com.example.assignment_java5.service.HoaDonService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
public class HoaDonServiceImpl implements HoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    private GioHangRepository gioHangRepository;

    @Autowired
    private GioHangChiTietRepository gioHangChiTietRepository;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private CartItemService cartItemService;

    @Override
    public List<HoaDon> getAll() {
        return hoaDonRepository.findAll();
    }

    @Autowired
    HttpServletRequest request;

    @Override
    public Page<HoaDon> fillAll(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return hoaDonRepository.findAll(pageable);
    }

    @Override
    public HoaDon addOrUpdate(HoaDonRequest hoaDonRequest) {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setId(hoaDonRequest.getId());
        hoaDon.setMa(new GenMa().maGen());
        hoaDon.setKhachHang(hoaDonRequest.getKhachHang());
        hoaDon.setNhanVien(hoaDonRequest.getNhanVien());
        hoaDon.setNgayTao(hoaDonRequest.getNgayTao());
        hoaDon.setNgayThanhToan(hoaDonRequest.getNgayThanhToan());
        hoaDon.setNgayShip(hoaDonRequest.getNgayShip());
        hoaDon.setNgayNhan(hoaDonRequest.getNgayNhan());
        hoaDon.setTenNguoiNhan(hoaDonRequest.getTenNguoiNhan());
        hoaDon.setDiaChi(hoaDonRequest.getDiaChi());
        hoaDon.setSdt(hoaDonRequest.getSdt());
        hoaDon.setEmail(hoaDonRequest.getEmail());
        hoaDon.setHinhThucThanhToan(hoaDonRequest.getHinhThucThanhToan());
        hoaDon.setTrangThai(hoaDonRequest.getTrangThai());
        return hoaDonRepository.save(hoaDon);
    }

    @Override
    public void delete(UUID id) {
        hoaDonRepository.deleteById(id);
    }

    @Override
    public HoaDon getById(UUID id) {
        return hoaDonRepository.getById(id);
    }

    @Override
    public HoaDon getByMa(String ma) {
        return hoaDonRepository.getByMa(ma);
    }

    @Override
    public void saveHoaDon(HoaDon hoaDon) {
        HoaDon hoaDonx = new HoaDon();
        LocalDate currentDate = LocalDate.now();
        String ma = generateInvoiceNumberHD();
        hoaDonx.setMa(ma);
        hoaDonx.setTenNguoiNhan(hoaDon.getTenNguoiNhan());
        hoaDonx.setSdt(hoaDon.getSdt());
        hoaDonx.setEmail(hoaDon.getEmail());
        hoaDonx.setDiaChi(hoaDon.getDiaChi());
        hoaDonx.setHinhThucThanhToan(hoaDon.getHinhThucThanhToan());
        hoaDonx.setNgayTao(Date.valueOf(currentDate));
        hoaDonx.setNgayThanhToan(Date.valueOf(currentDate));
        hoaDonx.setTrangThai(3);
        hoaDonRepository.save(hoaDonx);

        for (CartItem x : cartItemService.items()) {
            ChiTietSanPham ctsp = chiTietSanPhamRepository.getById(x.getIdCTSP());
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setHoaDon(getByMa(ma));
            hoaDonChiTiet.setChiTietSanPham(ctsp);
            hoaDonChiTiet.setDonGia(x.getGia());
            hoaDonChiTiet.setSoLuong(x.getSoLuong());
            Integer soLuongMoi = ctsp.getSoLuong() - x.getSoLuong();
            hoaDonChiTietRepository.save(hoaDonChiTiet);
            chiTietSanPhamRepository.updateSoLuong(soLuongMoi, x.getIdCTSP());
        }
    }

    @Override
    public void saveHoaDonNguoiDung(HoaDon hoaDon, UUID idKH) {
        GioHang gioHang = gioHangRepository.findGioHangByKhachHang(idKH);
        List<GioHangChiTiet> listGHCT = gioHangChiTietRepository.findGioHangChiTietByGioHangKhachHangId(idKH);
        KhachHang khachHang = khachHangRepository.getById(idKH);
        HoaDon hoaDonx = new HoaDon();
        GioHang gioHangx = new GioHang();
        LocalDate currentDate = LocalDate.now();
        String ma = generateInvoiceNumberHD();
        hoaDonx.setMa(ma);
        hoaDonx.setKhachHang(khachHang);
        hoaDonx.setTenNguoiNhan(hoaDon.getTenNguoiNhan());
        hoaDonx.setSdt(khachHang.getSdt());
        hoaDonx.setEmail(khachHang.getEmail());
        hoaDonx.setDiaChi(khachHang.getDiaChi());
        hoaDonx.setHinhThucThanhToan(hoaDon.getHinhThucThanhToan());
        hoaDonx.setNgayTao(Date.valueOf(currentDate));
        if (hoaDon.getHinhThucThanhToan().equalsIgnoreCase("Thanh toán khi nhận hàng")) {
            hoaDonx.setNgayThanhToan(null);
        } else {
            hoaDonx.setNgayThanhToan(Date.valueOf(currentDate));
        }
        hoaDonx.setTrangThai(3);

        hoaDonRepository.save(hoaDonx);

        for (GioHangChiTiet x : listGHCT) {
            ChiTietSanPham ctsp = chiTietSanPhamRepository.getById(x.getChiTietSanPham().getId());
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setHoaDon(getByMa(ma));
            hoaDonChiTiet.setChiTietSanPham(ctsp);
            hoaDonChiTiet.setDonGia(x.getDonGia());
            hoaDonChiTiet.setSoLuong(x.getSoLuong());
            Integer soLuongMoi = ctsp.getSoLuong() - x.getSoLuong();
            hoaDonChiTietRepository.save(hoaDonChiTiet);
            chiTietSanPhamRepository.updateSoLuong(soLuongMoi, x.getChiTietSanPham().getId());
        }
        gioHangChiTietRepository.deleteAll();
        gioHangRepository.deleteAll();
        HttpSession session = request.getSession();
        session.setAttribute("hoaDonS", hoaDonx);

    }

    @Override
    public HoaDon getByKhachHangId(UUID id) {
        return hoaDonRepository.getByKhachHangId(id);
    }

    @Override
    public Page<HoaDon> findAllSortedByTrangThai(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return hoaDonRepository.findAllSortedByTrangThai(pageable);
    }


    @Override
    public HoaDon findByMa(String ma) {
        return hoaDonRepository.findByMa(ma);
    }

    public String generateInvoiceNumberHD() {
        String prefix = "HD";
        int randomNumber = new Random().nextInt(90000); // Số ngẫu nhiên từ 0 đến 9999
        String invoiceNumber = prefix + randomNumber;
        return invoiceNumber;
    }
}
