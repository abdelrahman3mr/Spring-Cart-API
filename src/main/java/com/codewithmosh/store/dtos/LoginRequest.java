package com.codewithmosh.store.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Email must be provided")
    private String email;

    @NotBlank(message = "Password must be provided")
    private String password;
}
