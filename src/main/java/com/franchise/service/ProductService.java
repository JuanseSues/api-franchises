package com.franchise.service;

import com.franchise.dto.ProductDTO;
import com.franchise.dto.ProductDetailsDTO;
import com.franchise.dto.StockUpdateDTO;
import com.franchise.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for managing products.
 * Defines methods for adding, updating, and retrieving products.
 */
public interface ProductService {

    /**
     * Adds a new product to a specific branch.
     * @param branchId ID of the branch to which the product will be added.
     * @param productDTO Data of the product to be added.
     * @return Mono<Product> The created product.
     */
    Mono<Product> addProductToBranch(String branchId, ProductDTO productDTO);

    /**
     * Removes a product from a specific branch.
     * @param branchId ID of the branch from which the product will be removed.
     * @param productId ID of the product to be removed.
     * @return Mono<Void> A Mono signaling when the operation has completed.
     */
    Mono<Void> removeProductFromBranch(String branchId, String productId);

    /**
     * Updates the stock of a specific product.
     * @param productId ID of the product to be updated.
     * @param stockUpdateDTO Data containing the new stock value.
     * @return Mono<Product> The updated product.
     */
    Mono<Product> updateProductStock(String productId, StockUpdateDTO stockUpdateDTO);

    /**
     * Updates the name of a specific product.
     * @param productId ID of the product to be updated.
     * @param newName New name for the product.
     * @return Mono<Product> The updated product.
     */
    Mono<Product> updateProductName(String productId, String newName);

    /**
     * Retrieves the product with the highest stock for each branch in a specific franchise.
     * @param franchiseId ID of the franchise.
     * @return Flux<ProductDetailsDTO> List of products with the highest stock, including branch information.
     */
    Flux<ProductDetailsDTO> getProductsWithHighestStockPerBranch(String franchiseId);
}