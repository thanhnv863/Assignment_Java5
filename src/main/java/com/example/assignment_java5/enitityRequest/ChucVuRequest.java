package com.example.assignment_java5.enitityRequest;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChucVuRequest {
    private UUID id;

//    @NotBlank(message = "Mã không được để trống")
    private String ma;

    @NotBlank(message = "Tên không được để trống")
    private String ten;
}
