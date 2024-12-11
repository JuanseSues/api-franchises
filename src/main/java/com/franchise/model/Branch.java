package com.franchise.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a branch in the franchise system.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "branches")
public class Branch {

    /**
     * Unique identifier for the branch.
     */
    @Id
    private String id;

    /**
     * Name of the branch.
     * This field is required and cannot be blank.
     */
    @NotBlank(message = "Branch name is required")
    private String name;

    /**
     * List of products available in the branch.
     * This field is a reference to the Product collection.
     */
    @DBRef
    private List<Product> products = new ArrayList<>();

    /**
     * Identifier of the franchise to which the branch belongs.
     */
    private String franchiseId;
}