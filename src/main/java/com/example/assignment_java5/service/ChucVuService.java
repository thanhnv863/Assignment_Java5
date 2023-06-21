package com.example.assignment_java5.service;

import com.example.assignment_java5.enitityRequest.ChucVuRequest;
import com.example.assignment_java5.entity.ChucVu;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface ChucVuService {

    List<ChucVu> getAllChucVu();

    Page<ChucVu> page(Integer pageNo, Integer size);

    ChucVu addOrUpdate(ChucVuRequest chucVuRequest);

    void delete(UUID id);

    ChucVu getById(UUID id);

    ChucVu findByMa(String ma);

}
