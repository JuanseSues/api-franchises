package com.franchise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;

/**
 * Data Transfer Object for updating stock.
 * Used to transfer stock update data between layers.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockUpdateDTO {

    /**
     * Stock quantity of the product.
     * This field cannot be negative.
     */
    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stock;
}