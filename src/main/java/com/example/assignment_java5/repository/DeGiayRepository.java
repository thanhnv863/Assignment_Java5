package com.example.assignment_java5.repository;

import com.example.assignment_java5.entity.DeGiay;
import com.example.assignment_java5.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface DeGiayRepository extends JpaRepository<DeGiay, UUID> {
    @Query("select DISTINCT dg from DeGiay dg\n" +
            "         inner join ChiTietSanPham ctsp on ctsp.deGiay.id = dg.id\n" +
            "         inner join SanPham sp on sp.id = ctsp.sanPham.id\n" +
            "         where sp.ten like :tenSP")

    List<DeGiay> findAllByTen(@Param("tenSP") String tenSP);

    DeGiay findByMa(String ma);
}
