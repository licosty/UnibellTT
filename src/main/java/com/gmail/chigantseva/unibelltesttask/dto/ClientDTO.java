package com.gmail.chigantseva.unibelltesttask.dto;

import jakarta.validation.constraints.NotBlank;

public record ClientDTO (Integer id, @NotBlank String name) {}
