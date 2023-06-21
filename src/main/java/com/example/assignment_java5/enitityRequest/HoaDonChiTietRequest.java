package com.example.assignment_java5.enitityRequest;

import com.example.assignment_java5.entity.ChiTietSanPham;
import com.example.assignment_java5.entity.HoaDon;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class HoaDonChiTietRequest {
    private UUID id;

    private HoaDon hoaDon;

    private ChiTietSanPham chiTietSanPham;

    @NotNull(message = "Số lượng không được để trống")
    @Positive(message = "Số lượng phải là số nguyên dương")
    private Integer SoLuong;

    @NotNull(message = "Đơn giá không được để trống")
    @Positive(message = "Đơn giá phải là số nguyên dương")
    private BigDecimal donGia;
}
