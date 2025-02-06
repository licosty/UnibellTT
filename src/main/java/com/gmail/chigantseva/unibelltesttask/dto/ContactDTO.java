package com.gmail.chigantseva.unibelltesttask.dto;

import jakarta.validation.constraints.NotBlank;

public record ContactDTO (@NotBlank String contact, @NotBlank String type) {}
