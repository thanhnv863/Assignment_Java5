package com.example.assignment_java5.service;

import com.example.assignment_java5.enitityRequest.DongSPRequest;
import com.example.assignment_java5.entity.DongSP;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface DongSanPhamService {

    List<DongSP> getAll();
    Page<DongSP> fillAll(Integer pageNo, Integer size);

    DongSP addOrUpdate(DongSPRequest dongSPRequest);

    void delete(UUID id);

    DongSP getById(UUID id);
    List<DongSP> findAllByTen(String tenDSP);

    DongSP findByMa(String ma);

}
