package org.example.soapproduct2.repository;

import com.gproductservice.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Repository class for managing product data.
 * Initializes product data on application startup and provides a method to find a product by name.
 */
@Component
public class ProductRepository {

    /**
     * Map to store products, keyed by lowercase product names.
     */
    private static final Map<String, Product> products = new HashMap<>();

    /**
     * Initializes product data on application startup.
     */
    @PostConstruct
    public void initData() {
        // Sample product data initialization
        Product product1 = new Product("Laptop", 999.99, "ABC123");
        Product product2 = new Product("Smartphone", 599.99, "XYZ456");
        Product product3 = new Product("Headphones", 79.99, "DEF789");

        // Store products in the map, keyed by lowercase product names
        products.put(product1.getName().toLowerCase(), product1);
        products.put(product2.getName().toLowerCase(), product2);
        products.put(product3.getName().toLowerCase(), product3);
    }

    /**
     * Finds a product by name.
     *
     * @param name The name of the product to find.
     * @return The Product object if found, or null if not found.
     */
    public Product findProduct(String name) {
        return products.get(name.toLowerCase());
    }
}