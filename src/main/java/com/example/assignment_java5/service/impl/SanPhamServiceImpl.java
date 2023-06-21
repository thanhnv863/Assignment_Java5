package com.example.assignment_java5.service.impl;

import com.example.assignment_java5.enitityRequest.MauSacRequest;
import com.example.assignment_java5.enitityRequest.SanPhamRequest;
import com.example.assignment_java5.entity.SanPham;
import com.example.assignment_java5.genMa.GenMa;
import com.example.assignment_java5.repository.SanPhamRepository;
import com.example.assignment_java5.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Override
    public List<SanPham> getAll() {
        return sanPhamRepository.findAll();
    }

    @Override
    public Page<SanPham> fillAll(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return sanPhamRepository.findAll(pageable);
    }

    @Override
    public SanPham addOrUpdate(SanPhamRequest sanPhamRequest) {
        SanPham sanPham = new SanPham();
        sanPham.setId(sanPhamRequest.getId());
        sanPham.setMa(new GenMa().maGen());
        sanPham.setTen(sanPhamRequest.getTen());
        return sanPhamRepository.save(sanPham);
    }

    @Override
    public void delete(UUID id) {
        sanPhamRepository.deleteById(id);
    }

    @Override
    public SanPham getById(UUID id) {
        return sanPhamRepository.getById(id);
    }

    @Override
    public SanPham findByMa(String ma) {
        return sanPhamRepository.findByMa(ma);
    }
}
