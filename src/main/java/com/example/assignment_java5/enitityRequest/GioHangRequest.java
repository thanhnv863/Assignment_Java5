package com.example.assignment_java5.enitityRequest;

import com.example.assignment_java5.entity.KhachHang;
import com.example.assignment_java5.entity.NhanVien;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GioHangRequest {
    private UUID id;

    private KhachHang khachHang;

    private NhanVien nhanVien;
//    @NotBlank(message = "Mã không được để trống")
    private String ma;
    @NotNull(message = "Ngày tạo không được để trống")
    private Date ngayTao;
    private Date ngayThanhToan;
    @NotBlank(message = "Tên người nhận không được để trống")
    private String tenNguoiNhan;
    @NotBlank(message = "Địa chỉ không được để trống")
    private String diaChi;
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "\\d{10}", message = "Số điện thoại 10 chữ số")
    private String sdt;
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email phải đúng định dạng")
    private String email;
    @NotBlank(message = "Hình thức thanh toán không được để trống")
    private String hinhThucThanhToan;

    private Integer trangThai;
}
