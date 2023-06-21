package com.example.assignment_java5.service.impl;

import com.example.assignment_java5.enitityRequest.DongSPRequest;
import com.example.assignment_java5.entity.DongSP;
import com.example.assignment_java5.genMa.GenMa;
import com.example.assignment_java5.repository.DongSPRepository;
import com.example.assignment_java5.service.DongSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DongSanPhamServiceImpl implements DongSanPhamService {
    @Autowired
    private DongSPRepository dongSPRepository;

    @Override
    public List<DongSP> getAll() {
        return dongSPRepository.findAll();
    }

    @Override
    public Page<DongSP> fillAll(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return dongSPRepository.findAll(pageable);
    }


    @Override
    public DongSP addOrUpdate(DongSPRequest dongSPRequest) {
        DongSP dongSP = new DongSP();
        dongSP.setId(dongSPRequest.getId());
        dongSP.setMa(new GenMa().maGen());
        dongSP.setTen(dongSPRequest.getTen());
        return dongSPRepository.save(dongSP);
    }

    @Override
    public void delete(UUID id) {
        dongSPRepository.deleteById(id);
    }

    @Override
    public DongSP getById(UUID id) {
        return dongSPRepository.getById(id);
    }

    @Override
    public List<DongSP> findAllByTen(String tenDSP) {
        return dongSPRepository.findAllByTen(tenDSP);
    }

    @Override
    public DongSP findByMa(String ma) {
        return dongSPRepository.findByMa(ma);
    }
}
