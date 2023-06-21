package com.example.assignment_java5.repository;

import com.example.assignment_java5.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, UUID> {
    SanPham findByMa(String ma);
}
