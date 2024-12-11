package com.franchise.controller;

import com.franchise.dto.ProductDTO;
import com.franchise.dto.ProductDetailsDTO;
import com.franchise.dto.StockUpdateDTO;
import com.franchise.model.Product;
import com.franchise.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    // Injecting the ProductService using constructor injection
    private final ProductService productService;

    /**
     * Endpoint to add a new product to a specific branch.
     * @param branchId ID of the branch to which the product will be added.
     * @param productDTO Data of the product to be added.
     * @return Mono<Product> The created product.
     */
    @PostMapping("/branch/{branchId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> addProductToBranch(
            @PathVariable String branchId,
            @Valid @RequestBody ProductDTO productDTO) {
        return productService.addProductToBranch(branchId, productDTO);
    }

    /**
     * Endpoint to remove a product from a specific branch.
     * @param branchId ID of the branch from which the product will be removed.
     * @param productId ID of the product to be removed.
     * @return Mono<Void> A Mono signaling when the operation has completed.
     */
    @DeleteMapping("/branch/{branchId}/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> removeProductFromBranch(
            @PathVariable String branchId,
            @PathVariable String productId) {
        return productService.removeProductFromBranch(branchId, productId);
    }

    /**
     * Endpoint to update the stock of a specific product.
     * @param productId ID of the product to be updated.
     * @param stockUpdateDTO Data containing the new stock value.
     * @return Mono<Product> The updated product.
     */
    @PatchMapping("/{productId}/stock")
    public Mono<Product> updateProductStock(
            @PathVariable String productId,
            @Valid @RequestBody StockUpdateDTO stockUpdateDTO) {
        return productService.updateProductStock(productId, stockUpdateDTO);
    }

    /**
     * Endpoint to update the name of a specific product.
     * @param productId ID of the product to be updated.
     * @param newName New name for the product.
     * @return Mono<Product> The updated product.
     */
    @PutMapping("/{productId}/name")
    public Mono<Product> updateProductName(
            @PathVariable String productId,
            @RequestBody String newName) {
        return productService.updateProductName(productId, newName);
    }

    /**
     * Endpoint to get the product with the highest stock for each branch in a specific franchise.
     * @param franchiseId ID of the franchise.
     * @return Flux<ProductDetailsDTO> List of products with the highest stock, including branch information.
     */
    @GetMapping("/highest-stock/franchise/{franchiseId}")
    public Flux<ProductDetailsDTO> getProductsWithHighestStockPerBranch(
            @PathVariable String franchiseId) {
        return productService.getProductsWithHighestStockPerBranch(franchiseId);
    }
}