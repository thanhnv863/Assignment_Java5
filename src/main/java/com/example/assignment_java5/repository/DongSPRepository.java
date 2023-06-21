package com.example.assignment_java5.repository;

import com.example.assignment_java5.entity.DongSP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface DongSPRepository extends JpaRepository<DongSP, UUID> {
    @Query("select DISTINCT dsp from DongSP dsp\n" +
            "         inner join ChiTietSanPham ctsp on ctsp.dongSP.id = dsp.id\n" +
            "         inner join SanPham sp on sp.id = ctsp.sanPham.id\n" +
            "         where ctsp.sanPham.ten like :tenSP")
    List<DongSP> findAllByTen(@Param("tenSP") String tenSP);

    DongSP findByMa(String ma);
}
