package com.example.assignment_java5.repository;

import com.example.assignment_java5.entity.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, UUID> {
    List<HoaDonChiTiet> getByHoaDonMa(String ma);

//    List<HoaDonChiTiet> findByHoaDonKhachHangId(UUID id);

    @Query("select hdct from HoaDonChiTiet hdct where hdct.hoaDon.khachHang.id = ?1 ORDER BY case when hdct.hoaDon.trangThai = 0 then 0" +
            " when hdct.hoaDon.trangThai = 3 then 1" +
            " when hdct.hoaDon.trangThai = 2 then 2" +
            "ELSE 3 " +
            "end")
    List<HoaDonChiTiet> findByHoaDonKhachHangId(UUID id);

}
