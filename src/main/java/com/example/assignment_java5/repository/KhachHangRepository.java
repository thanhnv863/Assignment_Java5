package com.example.assignment_java5.repository;

import com.example.assignment_java5.entity.KhachHang;
import com.example.assignment_java5.response.UserRespone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, UUID> {
    KhachHang findKhachHangByEmailAndMatKhau(String email, String matKhau);

    KhachHang findByMa(String ma);
    @Transactional
    @Query("select kh.email, kh.matKhau, kh.chucVu.ten from KhachHang kh where kh.email = ?1")
    Optional<UserRespone> getByEmail(String email);

    @Query("select kh from KhachHang kh where kh.email like ?1")
    KhachHang getKhachHangByEmail(String email);
}
