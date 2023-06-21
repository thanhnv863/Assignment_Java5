package com.example.assignment_java5.service.impl;

import com.example.assignment_java5.enitityRequest.ChucVuRequest;
import com.example.assignment_java5.entity.ChucVu;
import com.example.assignment_java5.genMa.GenMa;
import com.example.assignment_java5.repository.ChucVuRepository;
import com.example.assignment_java5.service.ChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChucVuServiceImpl implements ChucVuService {
    @Autowired
    private ChucVuRepository chucVuRepository;

    @Override
    public List<ChucVu> getAllChucVu() {
        return chucVuRepository.findAll();

    }

    @Override
    public Page<ChucVu> page(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return chucVuRepository.findAll(pageable);
    }


    @Override
    public ChucVu addOrUpdate(ChucVuRequest chucVuRequest) {
        ChucVu chucVu = new ChucVu();
        chucVu.setId(chucVuRequest.getId());
        chucVu.setMa(new GenMa().maGen());
        chucVu.setTen(chucVuRequest.getTen());
        return chucVuRepository.save(chucVu);
    }

    @Override
    public void delete(UUID id) {
        ChucVu chucVu = getById(id);
        chucVuRepository.delete(chucVu);
    }

    @Override
    public ChucVu getById(UUID id) {
        return chucVuRepository.getById(id);
    }

    @Override
    public ChucVu findByMa(String ma) {
        return chucVuRepository.findByMa(ma);
    }
}
