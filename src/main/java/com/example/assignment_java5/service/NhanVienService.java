package com.example.assignment_java5.service;

import com.example.assignment_java5.enitityRequest.NhanVienRequest;
import com.example.assignment_java5.entity.NhanVien;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface NhanVienService {
    List<NhanVien> getAll();

    Page<NhanVien> fillAll(Integer pageNo, Integer size);

    NhanVien addOrUpdate(NhanVienRequest nhanVienRequest);

    void delete(UUID id);

    NhanVien getById(UUID id);

//    NhanVien findNhanVienByEmailAndMatKhau(String email, String matKhau);

//    NhanVien add(NhanVien nhanVien);

    NhanVien findByMa(String ma);

}
