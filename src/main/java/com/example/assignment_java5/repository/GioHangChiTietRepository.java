package com.example.assignment_java5.repository;

import com.example.assignment_java5.entity.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, UUID> {
    @Query("SELECT ghct from GioHangChiTiet ghct " +
            "inner join GioHang gh on ghct.gioHang.id = gh.id inner join KhachHang kh on kh.id = gh.khachHang.id where kh.id = ?1")
    List<GioHangChiTiet> findGioHangChiTietByGioHangKhachHangId(UUID id);

    List<GioHangChiTiet> findGioHangChiTietByGioHangId(UUID id);

    List<GioHangChiTiet> findGioHangChiTietById(UUID id);

    GioHangChiTiet findGioHangChiTietByGioHangIdAndAndChiTietSanPhamId(UUID idGH, UUID idCTSP);
    @Transactional
    @Modifying
    void deleteGioHangChiTietByChiTietSanPhamId(UUID id);

    @Transactional
    @Modifying
    @Query("UPDATE GioHangChiTiet ghct set ghct.SoLuong =?1 where ghct.id =?2")
    void updateSoLuongGHCT(Integer soLuongMoi,UUID id);

}
