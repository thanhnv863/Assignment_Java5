package com.example.assignment_java5.service.impl;

import com.example.assignment_java5.enitityRequest.MauSacRequest;
import com.example.assignment_java5.entity.MauSac;
import com.example.assignment_java5.genMa.GenMa;
import com.example.assignment_java5.repository.MauSacRepository;
import com.example.assignment_java5.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MauSacServiceImpl implements MauSacService {
    @Autowired
    private MauSacRepository mauSacRepository;


    @Override
    public List<MauSac> getAll() {
        return mauSacRepository.findAll();
    }

    @Override
    public Page<MauSac> fillAll(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return mauSacRepository.findAll(pageable);
    }


    @Override
    public MauSac addOrUpdate(MauSacRequest mauSacRequest) {
        MauSac mauSac = new MauSac();
        mauSac.setId(mauSacRequest.getId());
        mauSac.setMa(new GenMa().maGen());
        mauSac.setTen(mauSacRequest.getTen());
        return mauSacRepository.save(mauSac);
    }

    @Override
    public void delete(UUID id) {
        mauSacRepository.deleteById(id);
    }

    @Override
    public MauSac getById(UUID id) {
        return mauSacRepository.getById(id);
    }

    @Override
    public List<MauSac> findAllByTen(String tenMS) {
        return mauSacRepository.findAllByTen(tenMS);
    }

    @Override
    public MauSac findByMa(String ma) {
        return mauSacRepository.findByMa(ma);
    }
}
