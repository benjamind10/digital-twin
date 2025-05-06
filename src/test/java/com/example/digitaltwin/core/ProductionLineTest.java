package com.example.digitaltwin.core;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ProductionLineTest {

    @Test
    public void testProcessingWithoutScrap() {
        // Create a production line with fixed machines
        ProductionLine line = new ProductionLine("TestLine") {
            // @Override
            // protected boolean isProductScrapped(Product product) {
            //     return false; // override to disable random scrap
            // }
        };

        line.addMachine(new Machine("Stage1"));
        line.addMachine(new Machine("Stage2"));

        Product p1 = new Product("TypeA");
        Product p2 = new Product("TypeB");

        line.feedProduct(p1);
        line.feedProduct(p2);

        // simulate full processing
        while (!line.isProcessingComplete()) {
            line.processStep();
        }

        List<Product> completed = line.getCompletedProducts();
        assertEquals(2, completed.size());
        assertEquals(0, line.getTotalScrapped());
        assertEquals(2, line.getTotalProduced());

        for (Product p : completed) {
            assertTrue(p.isCompleted());
        }
    }
}
