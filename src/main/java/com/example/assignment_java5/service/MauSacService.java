package com.example.assignment_java5.service;

import com.example.assignment_java5.enitityRequest.MauSacRequest;
import com.example.assignment_java5.entity.MauSac;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface MauSacService {
    List<MauSac> getAll();

    Page<MauSac> fillAll(Integer pageNo, Integer size);

    MauSac addOrUpdate(MauSacRequest mauSacRequest);

    void delete(UUID id);

    MauSac getById(UUID id);

    List<MauSac> findAllByTen(String tenMS);

    MauSac findByMa(String ma);

}
