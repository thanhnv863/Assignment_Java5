package com.example.assignment_java5.repository;

import com.example.assignment_java5.entity.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GioHangRepository extends JpaRepository<GioHang, UUID> {
    GioHang getByMa(String ma);

    @Query("select gh from GioHang gh " +
            "inner join KhachHang kh on gh.khachHang.id = kh.id where kh.id = ?1")
    GioHang findGioHangByKhachHang(UUID idKH);

    GioHang findByMa(String ma);

}
