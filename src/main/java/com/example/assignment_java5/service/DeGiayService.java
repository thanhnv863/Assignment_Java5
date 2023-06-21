package com.example.assignment_java5.service;

import com.example.assignment_java5.enitityRequest.DeGiayRequest;
import com.example.assignment_java5.entity.DeGiay;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface DeGiayService {

    List<DeGiay> getAll();

    Page<DeGiay> fillAll(Integer pageNo, Integer size);

    DeGiay addOrUpdate(DeGiayRequest deGiayRequest);

    void delete(UUID id);

    DeGiay getById(UUID id);

    List<DeGiay> findAllByTen(String tenDG);

    DeGiay findByMa(String ma);

}
