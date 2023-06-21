package com.example.assignment_java5.service.impl;

import com.example.assignment_java5.enitityRequest.NhanVienRequest;
import com.example.assignment_java5.entity.NhanVien;
import com.example.assignment_java5.genMa.GenMa;
import com.example.assignment_java5.repository.NhanVienRepository;
import com.example.assignment_java5.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NhanVienServiceImpl implements NhanVienService {
    @Autowired
    private NhanVienRepository nhanVienRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public List<NhanVien> getAll() {
        return nhanVienRepository.findAll();
    }

    @Override
    public Page<NhanVien> fillAll(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return nhanVienRepository.findAll(pageable);
    }


    @Override
    public NhanVien addOrUpdate(NhanVienRequest nhanVienRequest) {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setId(nhanVienRequest.getId());
        nhanVien.setChucVu(nhanVienRequest.getChucVu());
        nhanVien.setMa(new GenMa().maGen());
        nhanVien.setHoTen(nhanVienRequest.getHoTen());
        nhanVien.setSdt(nhanVienRequest.getSdt());
        nhanVien.setEmail(nhanVienRequest.getEmail());
        nhanVien.setNgaySinh(nhanVienRequest.getNgaySinh());
        nhanVien.setGioiTinh(nhanVienRequest.getGioiTinh());
        nhanVien.setDiaChi(nhanVienRequest.getDiaChi());
        nhanVien.setTrangThai(nhanVienRequest.getTrangThai());
        nhanVien.setTaiKhoan(nhanVienRequest.getTaiKhoan());
        nhanVien.setMatKhau(nhanVienRequest.getMatKhau());
        return nhanVienRepository.save(nhanVien);
    }

    @Override
    public void delete(UUID id) {
        nhanVienRepository.deleteById(id);
    }

    @Override
    public NhanVien getById(UUID id) {
        return nhanVienRepository.getById(id);
    }

    @Override
    public NhanVien findByMa(String ma) {
        return nhanVienRepository.findByMa(ma);
    }


//    @Override
//    public NhanVien add(NhanVien nhanVien) {
//        nhanVien.setMatKhau(passwordEncoder.encode(nhanVien.getMatKhau()));
//        return nhanVienRepository.save(nhanVien);
//    }

//    @Override
//    public NhanVien findNhanVienByEmailAndMatKhau(String email, String matKhau) {
//        return nhanVienRepository.findNhanVienByEmailAndMatKhau(email, matKhau);
//    }
}
