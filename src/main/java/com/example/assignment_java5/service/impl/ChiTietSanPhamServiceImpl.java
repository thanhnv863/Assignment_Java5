package com.example.assignment_java5.service.impl;

import com.example.assignment_java5.enitityRequest.ChiTietSanPhamRequest;
import com.example.assignment_java5.entity.ChiTietSanPham;
import com.example.assignment_java5.repository.ChiTietSanPhamRepository;
import com.example.assignment_java5.service.ChiTietSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class ChiTietSanPhamServiceImpl implements ChiTietSanPhamService {

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Override
    public List<ChiTietSanPham> getAll() {
        return chiTietSanPhamRepository.findAll();
    }

    @Override
    public Page<ChiTietSanPham> fillAll(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return chiTietSanPhamRepository.findAll(pageable);
    }

    @Override
    public ChiTietSanPham addOrUpdate(ChiTietSanPhamRequest chiTietSanPhamRequest, MultipartFile file) throws IOException {
        String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/images";
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
        fileNames.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());

        ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
        chiTietSanPham.setId(chiTietSanPhamRequest.getId());
        chiTietSanPham.setSanPham(chiTietSanPhamRequest.getSanPham());
        chiTietSanPham.setDongSP(chiTietSanPhamRequest.getDongSP());
        chiTietSanPham.setDeGiay(chiTietSanPhamRequest.getDeGiay());
        chiTietSanPham.setMauSac(chiTietSanPhamRequest.getMauSac());
        chiTietSanPham.setNgayNhapHang(chiTietSanPhamRequest.getNgayNhapHang());
        chiTietSanPham.setDonGia(chiTietSanPhamRequest.getDonGia());
        chiTietSanPham.setSoLuong(chiTietSanPhamRequest.getSoLuong());
        chiTietSanPham.setAnh(String.valueOf(file.getOriginalFilename()));
        chiTietSanPham.setXuatXu(chiTietSanPhamRequest.getXuatXu());
        chiTietSanPham.setKichCo(chiTietSanPhamRequest.getKichCo());
        chiTietSanPham.setTrangThai(chiTietSanPhamRequest.getTrangThai());
            return chiTietSanPhamRepository.save(chiTietSanPham);
    }

    @Override
    public void delete(UUID id) {
        chiTietSanPhamRepository.deleteById(id);
    }

    @Override
    public ChiTietSanPham getById(UUID id) {
        return chiTietSanPhamRepository.getById(id);
    }

    @Override
    public List<ChiTietSanPham> findAllByKichCo(String tenSP, String tenMau) {
        return chiTietSanPhamRepository.findAllByKichCo(tenSP, tenMau);
    }

    @Override
    public void updateSoLuong(Integer soLuongMoi, UUID idCTSP) {
         chiTietSanPhamRepository.updateSoLuong(soLuongMoi, idCTSP);
    }

    @Override
    public ChiTietSanPham getChiTietSanPhamBySanPhamTenAndMauSacTen(String tenSP, String tenMS) {
        return chiTietSanPhamRepository.getChiTietSanPhamBySanPhamTenAndMauSacTen(tenSP, tenMS);
    }


}
