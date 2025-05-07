package com.example.digitaltwin.core;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ProductTest {

    @Test
    public void testProductInitialization() {
        List<String> route = List.of("Cut", "Drill", "Assemble");
        Product product = new Product("Widget", route);

        assertNotNull(product.getId());
        assertEquals("Widget", product.getType());
        assertNotNull(product.getCreatedAt());
        assertNull(product.getCompletedAt());
        assertEquals("Cut", product.getCurrentMachineName());
        assertFalse(product.isCompleted());
        assertFalse(product.isFinished());
    }

    @Test
    public void testProductLifecycle() {
        List<String> route = List.of("Cutter", "Welder", "Assembler");
        Product product = new Product("Gadget", route);

        // Simulate going through all machines
        product.advanceStep(); // Cutter
        product.advanceStep(); // Welder
        product.advanceStep(); // Assembler

        assertTrue(product.isFinished());

        product.markCompleted();

        assertTrue(product.isCompleted());
        assertNotNull(product.getCompletedAt());
        assertTrue(product.getCompletedAt().isAfter(product.getCreatedAt()));
    }
}
