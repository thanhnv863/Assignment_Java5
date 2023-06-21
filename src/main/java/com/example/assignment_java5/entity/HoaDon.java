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

import java.sql.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name ="HoaDon")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "IdKH")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "IdNV")
    private NhanVien nhanVien;
    private String ma;
    private Date ngayTao;
    private Date ngayThanhToan;
    private Date ngayShip;
    private Date ngayNhan;
    private String tenNguoiNhan;
    private String diaChi;
    private String sdt;
    private String email;
    private String hinhThucThanhToan;
    private Integer trangThai;
}
