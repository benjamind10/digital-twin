package com.example.digitaltwin.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ProductTest {

    @Test
    public void testProductInitialization() {
        Product product = new Product("Widget");

        assertNotNull(product.getId());
        assertEquals("Widget", product.getType());
        assertEquals(0, product.getStep());
        assertNotNull(product.getCreatedAt());
        // assertNull(product.getCompletedA());
    }

    @Test
    public void testProductLifecycle() {
        Product product = new Product("Gadget");
        product.advanceStep();
        product.advanceStep();
        product.markCompleted();

        assertEquals(2, product.getStep());
        assertNotNull(product.getCompletedAt());
        assertTrue(product.isCompleted());
        assertTrue(product.getCompletedAt().isAfter(product.getCreatedAt()));
    }
}
