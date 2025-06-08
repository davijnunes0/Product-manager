package com.davijnunes.app.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRecordDto(
    @NotBlank String productName, 
    @NotBlank String productDescription,
    @NotNull BigDecimal productPrice
    ) {
    
}
