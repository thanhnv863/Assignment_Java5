package com.example.assignment_java5.enitityRequest;

import com.example.assignment_java5.entity.DeGiay;
import com.example.assignment_java5.entity.DongSP;
import com.example.assignment_java5.entity.MauSac;
import com.example.assignment_java5.entity.SanPham;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChiTietSanPhamRequest {
    private UUID id;

    private SanPham sanPham;

    private DongSP dongSP;

    private DeGiay deGiay;

    private MauSac mauSac;

    @NotNull(message = "Ngày nhập hàng không để trống")
    private Date ngayNhapHang;

    @NotNull(message = "Đơn giá không để trống")
    private BigDecimal donGia;

    @NotNull(message = "Số lượng không để trống")
    private Integer soLuong;

    private MultipartFile anh;

    @NotBlank(message = "Xuất xứ không để trống")
    private String xuatXu;

    @NotBlank(message = "Kích cỡ không để trống")
    private String kichCo;

    private Integer trangThai;
}
