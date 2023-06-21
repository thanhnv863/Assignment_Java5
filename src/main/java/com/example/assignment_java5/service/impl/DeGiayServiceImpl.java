package com.example.assignment_java5.service.impl;

import com.example.assignment_java5.enitityRequest.DeGiayRequest;
import com.example.assignment_java5.entity.DeGiay;
import com.example.assignment_java5.genMa.GenMa;
import com.example.assignment_java5.repository.DeGiayRepository;
import com.example.assignment_java5.service.DeGiayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DeGiayServiceImpl implements DeGiayService {
    @Autowired
    private DeGiayRepository deGiayRepository;


    @Override
    public List<DeGiay> getAll() {
        return deGiayRepository.findAll();
    }

    @Override
    public Page<DeGiay> fillAll(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return deGiayRepository.findAll(pageable);
    }


    @Override
    public DeGiay addOrUpdate(DeGiayRequest deGiayRequest) {
        DeGiay deGiay = new DeGiay();
        deGiay.setId(deGiayRequest.getId());
        deGiay.setMa(new GenMa().maGen());
        deGiay.setTen(deGiayRequest.getTen());
        return deGiayRepository.save(deGiay);
    }

    @Override
    public void delete(UUID id) {
        deGiayRepository.deleteById(id);
    }

    @Override
    public DeGiay getById(UUID id) {
        return deGiayRepository.getById(id);
    }

    @Override
    public List<DeGiay> findAllByTen(String tenDG) {
        return deGiayRepository.findAllByTen(tenDG);
    }

    @Override
    public DeGiay findByMa(String ma) {
        return deGiayRepository.findByMa(ma);
    }
}
