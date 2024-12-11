package com.franchise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Product Details.
 * Used to transfer detailed product data between layers.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsDTO {

    /**
     * ID of the product.
     */
    private String productId;

    /**
     * Name of the product.
     */
    private String productName;

    /**
     * Stock quantity of the product.
     */
    private Integer stock;

    /**
     * ID of the branch where the product is available.
     */
    private String branchId;

    /**
     * Name of the branch where the product is available.
     */
    private String branchName;
}