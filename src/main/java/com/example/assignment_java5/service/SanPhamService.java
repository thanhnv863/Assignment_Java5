package com.example.assignment_java5.service;

import com.example.assignment_java5.enitityRequest.SanPhamRequest;
import com.example.assignment_java5.entity.SanPham;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface SanPhamService {
    List<SanPham> getAll();

    Page<SanPham> fillAll(Integer pageNo, Integer size);

    SanPham addOrUpdate(SanPhamRequest sanPhamRequest);

    void delete(UUID id);

    SanPham getById(UUID id);

    SanPham findByMa(String ma);
}
