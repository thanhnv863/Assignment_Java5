package com.example.assignment_java5.repository;

import com.example.assignment_java5.entity.ChucVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface ChucVuRepository extends JpaRepository<ChucVu, UUID> {
    ChucVu findByMa(String ma);
}
