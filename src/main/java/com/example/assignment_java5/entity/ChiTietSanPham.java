package com.example.assignment_java5.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name ="ChiTietSP")
public class ChiTietSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "IdSP")
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "IdDongSP")
    private DongSP dongSP;

    @ManyToOne
    @JoinColumn(name = "IdDeGiay")
    private DeGiay deGiay;

    @ManyToOne
    @JoinColumn(name = "IdMauSac")
    private MauSac mauSac;

    private Date ngayNhapHang;

    private BigDecimal donGia;

    private Integer soLuong;

    private String anh;

    private String xuatXu;

    private String kichCo;

    private Integer trangThai;
}
