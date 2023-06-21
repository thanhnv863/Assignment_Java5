package com.example.assignment_java5.repository;

import com.example.assignment_java5.entity.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, UUID> {
    HoaDon getByMa(String ma);

    HoaDon getByKhachHangId(UUID id);

    @Query("SELECT hd FROM HoaDon hd ORDER BY CASE " +
            "WHEN hd.trangThai = 0 THEN 0 " +
            "WHEN hd.trangThai = 3 THEN 1 " +
            "WHEN hd.trangThai = 2 THEN 2 " +
            "ELSE 3 " +
            "END")
    Page<HoaDon> findAllSortedByTrangThai(Pageable pageable);

    HoaDon findByMa(String ma);

}
