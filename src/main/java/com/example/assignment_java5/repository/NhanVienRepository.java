package com.example.assignment_java5.repository;

import com.example.assignment_java5.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, UUID> {
    Optional<NhanVien> findByTaiKhoan(String taiKhoan);

    NhanVien findNhanVienByEmailAndMatKhau(String email, String matKhau);

    NhanVien findByMa(String ma);
}
