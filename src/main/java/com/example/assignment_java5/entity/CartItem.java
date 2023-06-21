package com.example.assignment_java5.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItem {
    private UUID idCTSP;
    private String tenSP;

    private String anh;

    private BigDecimal gia;

    private Integer soLuong;

}
