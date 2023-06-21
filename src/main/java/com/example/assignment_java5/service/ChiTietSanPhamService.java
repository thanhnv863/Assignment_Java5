package com.example.assignment_java5.service;

import com.example.assignment_java5.enitityRequest.ChiTietSanPhamRequest;
import com.example.assignment_java5.entity.ChiTietSanPham;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface ChiTietSanPhamService {
    List<ChiTietSanPham> getAll();

    Page<ChiTietSanPham> fillAll(Integer pageNo, Integer size);

    ChiTietSanPham addOrUpdate(ChiTietSanPhamRequest chiTietSanPhamRequest, MultipartFile file) throws IOException;

    void delete(UUID id);

    ChiTietSanPham getById(UUID id);

    List<ChiTietSanPham> findAllByKichCo(String kichCo, String tenMau);

    void updateSoLuong(Integer soLuongMoi, UUID idCTSP);

    ChiTietSanPham getChiTietSanPhamBySanPhamTenAndMauSacTen(String tenSP, String tenMS);

}
