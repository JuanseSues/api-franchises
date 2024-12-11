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
 * Represents a franchise in the franchise system.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "franchises")
public class Franchise {

    /**
     * Unique identifier for the franchise.
     */
    @Id
    private String id;

    /**
     * Name of the franchise.
     * This field is required and cannot be blank.
     */
    @NotBlank(message = "Franchise name is required")
    private String name;

    /**
     * List of branches under the franchise.
     * This field is a reference to the Branch collection.
     */
    @DBRef
    private List<Branch> branches = new ArrayList<>();
}