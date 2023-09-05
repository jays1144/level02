package com.example.jwttest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @NotBlank
    @Pattern(regexp = "/^[a-z0-9_-]{4,10}$/")
    private String username;
    @NotBlank
    @Pattern(regexp = "/^[a-zA-Z0-9_-]{8,15}$/")
    private String password;
}
