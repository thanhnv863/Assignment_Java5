package com.example.assignment_java5.repository;

import com.example.assignment_java5.entity.ChiTietSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham, UUID> {
    @Query("select DISTINCT ctsp from ChiTietSanPham ctsp" +
            "         where ctsp.sanPham.ten like :tenSP and ctsp.mauSac.ten like :tenMau")
    List<ChiTietSanPham> findAllByKichCo(@Param("tenSP") String tenSP, @Param("tenMau") String tenMau);

    @Transactional
    @Modifying
    @Query("update ChiTietSanPham ctsp set ctsp.soLuong = :soLuongMoi where ctsp.id = :idCTSP")
    void updateSoLuong(@Param("soLuongMoi") Integer soLuongMoi, @Param("idCTSP") UUID idCTSP);

//    @Query("select ctsp from ChiTietSanPham ctsp inner join MauSac ms on ms.id= ctsp.mauSac.id where ctsp.sanPham.ten = ?1 and ms.id = ?2")
    ChiTietSanPham getChiTietSanPhamBySanPhamTenAndMauSacTen(String tenSP, String tenMS);

}
