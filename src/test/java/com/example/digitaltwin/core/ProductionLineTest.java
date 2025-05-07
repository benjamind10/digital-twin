package com.example.digitaltwin.core;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ProductionLineTest {

    @Test
    public void testProductWithCustomRouteCompletesSuccessfully() {
        ProductionLine line = new ProductionLine("TestLine");

        // Register machines
        line.addMachine(new Machine("Cut"));
        line.addMachine(new Machine("Drill"));
        line.addMachine(new Machine("Assemble"));

        // Custom route for product
        List<String> route = List.of("Cut", "Drill", "Assemble");
        Product product = new Product("Widget", route);

        line.feedProduct(product);

        // Disable scrap for deterministic testing
        ProductionLine testLine = new ProductionLine("TestLine") {
            @Override
            protected boolean isProductScrapped(Product p) {
                return false;
            }

            @Override
            public void addMachine(Machine machine) {
                super.addMachine(machine);
            }
        };

        testLine.addMachine(new Machine("Cut"));
        testLine.addMachine(new Machine("Drill"));
        testLine.addMachine(new Machine("Assemble"));
        testLine.feedProduct(new Product("Widget", route));

        while (!testLine.isProcessingComplete()) {
            testLine.processStep();
        }

        assertEquals(1, testLine.getTotalProduced());
        assertEquals(0, testLine.getTotalScrapped());

        Product completed = testLine.getCompletedProducts().get(0);
        assertNotNull(completed.getCompletedAt());
        assertTrue(Duration.between(completed.getCreatedAt(), completed.getCompletedAt()).toMillis() >= 0);
    }

    @Test
    public void testProductScrappedIsNotProduced() {
        ProductionLine line = new ProductionLine("TestLine") {
            @Override
            protected String getScrapReason(Product p) {
                return "ForcedScrap"; // override to always scrap
            }
        };

        line.addMachine(new Machine("Cut"));
        line.addMachine(new Machine("Weld"));

        List<String> route = List.of("Cut", "Weld");
        Product product = new Product("Gadget", route);

        line.feedProduct(product);

        while (!line.isProcessingComplete()) {
            line.processStep();
        }

        assertEquals(0, line.getTotalProduced());
        assertEquals(1, line.getTotalScrapped());
    }

}
