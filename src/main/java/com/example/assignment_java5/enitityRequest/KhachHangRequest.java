package com.example.assignment_java5.enitityRequest;

import com.example.assignment_java5.entity.ChucVu;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class KhachHangRequest {
    private UUID id;
    @NotBlank(message = "Mã không được để trống")
    private String ma;
//    @NotBlank(message = "Họ tên không được để trống")
    private String hoTen;
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "\\d{10}", message = "Số điện thoại 10 chữ số")
    private String sdt;
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email phải đúng định dạng")
    private String email;
    @NotNull(message = "Ngày sinh không được để trống")
    private Date ngaySinh;
    @NotBlank(message = "Địa chỉ không được để trống")
    private String diaChi;
    private Integer trangThai;
    @NotBlank(message = "Tài khoản không được để trống")
    @Pattern(regexp = "^[^\\u0300-\\u036F]+$", message = "Tài khoản không được chứa dấu")
    private String taiKhoan;
    @NotBlank(message = "Mật khẩu không được để trống")
    @Pattern(regexp = "^[^\\u0300-\\u036F]+$", message = "Mật khẩu không được chứa dấu")
    private String matKhau;

    private ChucVu chucVu;
}
