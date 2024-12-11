package com.franchise.service.impl;

import com.franchise.dto.ProductDTO;
import com.franchise.dto.ProductDetailsDTO;
import com.franchise.dto.StockUpdateDTO;
import com.franchise.exception.ResourceNotFoundException;
import com.franchise.model.Product;
import com.franchise.repository.BranchRepository;
import com.franchise.repository.ProductRepository;
import com.franchise.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    // Injecting the ProductRepository and BranchRepository using constructor injection
    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;

    /**
     * Adds a new product to a specific branch.
     * @param branchId ID of the branch to which the product will be added.
     * @param productDTO Data of the product to be added.
     * @return Mono<Product> The created product.
     */
    @Override
    public Mono<Product> addProductToBranch(String branchId, ProductDTO productDTO) {
        return branchRepository.findById(branchId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Branch not found with id: " + branchId)))
                .flatMap(branch -> {
                    Product product = Product.builder()
                            .name(productDTO.getName())
                            .stock(productDTO.getStock())
                            .build();
                    return productRepository.save(product)
                            .doOnSuccess(savedProduct -> {
                                branch.getProducts().add(savedProduct);
                                branchRepository.save(branch).subscribe();
                            });
                });
    }

    /**
     * Removes a product from a specific branch.
     * @param branchId ID of the branch from which the product will be removed.
     * @param productId ID of the product to be removed.
     * @return Mono<Void> A Mono signaling when the operation has completed.
     */
    @Override
    public Mono<Void> removeProductFromBranch(String branchId, String productId) {
        return branchRepository.findById(branchId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Branch not found with id: " + branchId)))
                .flatMap(branch -> {
                    branch.getProducts().removeIf(product -> product.getId().equals(productId));
                    return branchRepository.save(branch)
                            .then(productRepository.deleteById(productId));
                });
    }

    /**
     * Updates the stock of a specific product.
     * @param productId ID of the product to be updated.
     * @param stockUpdateDTO Data containing the new stock value.
     * @return Mono<Product> The updated product.
     */
    @Override
    public Mono<Product> updateProductStock(String productId, StockUpdateDTO stockUpdateDTO) {
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Product not found with id: " + productId)))
                .map(product -> {
                    product.setStock(stockUpdateDTO.getStock());
                    return product;
                })
                .flatMap(productRepository::save);
    }

    /**
     * Updates the name of a specific product.
     * @param productId ID of the product to be updated.
     * @param newName New name for the product.
     * @return Mono<Product> The updated product.
     */
    @Override
    public Mono<Product> updateProductName(String productId, String newName) {
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Product not found with id: " + productId)))
                .map(product -> {
                    product.setName(newName);
                    return product;
                })
                .flatMap(productRepository::save);
    }

    /**
     * Retrieves the product with the highest stock for each branch in a specific franchise.
     * @param franchiseId ID of the franchise.
     * @return Flux<ProductDetailsDTO> List of products with the highest stock, including branch information.
     */
    @Override
    public Flux<ProductDetailsDTO> getProductsWithHighestStockPerBranch(String franchiseId) {
        return branchRepository.findByFranchiseId(franchiseId)
                .flatMap(branch -> Flux.fromIterable(branch.getProducts())
                        .sort(Comparator.comparing(Product::getStock).reversed())
                        .take(1)
                        .map(product -> ProductDetailsDTO.builder()
                                .productId(product.getId())
                                .productName(product.getName())
                                .stock(product.getStock())
                                .branchId(branch.getId())
                                .branchName(branch.getName())
                                .build()));
    }
}