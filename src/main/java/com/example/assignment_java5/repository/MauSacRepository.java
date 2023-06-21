package com.example.assignment_java5.repository;

import com.example.assignment_java5.entity.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface MauSacRepository extends JpaRepository<MauSac, UUID> {
    @Query("select DISTINCT ms from MauSac ms\n" +
            "         inner join ChiTietSanPham ctsp on ctsp.mauSac.id = ms.id\n" +
            "         inner join SanPham sp on sp.id = ctsp.sanPham.id\n" +
            "         where ctsp.sanPham.ten like :tenSP")
    List<MauSac> findAllByTen(@Param("tenSP") String tenSP);

    MauSac findByMa(String ma);
}
