package com.example.usermanagmentservice.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthanticationDto(
        @NotBlank String password,
        @NotBlank String username
) {
}
