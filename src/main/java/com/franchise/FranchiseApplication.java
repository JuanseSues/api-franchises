package com.franchise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;

/**
 * Main class for the Franchise Management application.
 * This class bootstraps the Spring Boot application.
 */
@SpringBootApplication
@EnableReactiveMongoAuditing
public class FranchiseApplication {

    /**
     * The main method that serves as the entry point for the Spring Boot application.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(FranchiseApplication.class, args);
    }
}