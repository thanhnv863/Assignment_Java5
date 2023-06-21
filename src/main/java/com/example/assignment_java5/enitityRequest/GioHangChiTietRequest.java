package com.example.assignment_java5.enitityRequest;

import com.example.assignment_java5.entity.ChiTietSanPham;
import com.example.assignment_java5.entity.GioHang;
import jakarta.validation.constraints.NotNull;
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
public class GioHangChiTietRequest {
    private UUID id;

    private GioHang gioHang;

    private ChiTietSanPham chiTietSanPham;
    @NotNull(message = "Số lượng không được để trống")
    private Integer SoLuong;
    @NotNull(message = "Đơn giá không được để trống")
    private BigDecimal donGia;
}
