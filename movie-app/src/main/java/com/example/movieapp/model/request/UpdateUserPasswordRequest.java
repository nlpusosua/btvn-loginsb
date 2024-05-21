package com.example.movieapp.model.request;

import jakarta.validation.constraints.NotBlank;

import lombok.*;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateUserPasswordRequest {
    int id;
    @NotBlank(message = "Mật khẩu cũ không được để trống")
    String oPassword;
    @NotBlank(message = "Mật khẩu mới không được để trống")
    String nPassword;
}