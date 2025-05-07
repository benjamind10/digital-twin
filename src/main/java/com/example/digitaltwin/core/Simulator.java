package com.example.digitaltwin.core;

import java.util.List;

public class Simulator {

    private final ProductionLine productionLine;

    public Simulator() {
        this.productionLine = new ProductionLine("MainLine");

        // Add all required machines (can be reused across product routes)
        productionLine.addMachine(new Machine("Cutter"));
        productionLine.addMachine(new Machine("Welder"));
        productionLine.addMachine(new Machine("Assembler"));
        productionLine.addMachine(new Machine("Paint"));
        productionLine.addMachine(new Machine("Pack"));

        // Define custom routes per product type
        List<String> widgetRoute = List.of("Cutter", "Welder", "Assembler");
        List<String> gadgetRoute = List.of("Cutter", "Paint", "Pack");

        // Feed in products with their own routes
        productionLine.feedProduct(new Product("Widget", widgetRoute));
        productionLine.feedProduct(new Product("Widget", widgetRoute));
        productionLine.feedProduct(new Product("Gadget", gadgetRoute));
        productionLine.feedProduct(new Product("Gadget", gadgetRoute));
    }

    public void run() {
        System.out.println("🔁 Starting Production Line Simulation...");

        int cycle = 0;
        while (!productionLine.isProcessingComplete()) {
            System.out.printf("⏱️ Cycle %d%n", ++cycle);
            productionLine.processStep();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }

            System.out.println();
        }

        System.out.println("✅ Simulation Complete.");

        System.out.printf("🏁 Final Metrics for %s%n", productionLine.getName());
        System.out.printf("   ✔️ Total Produced: %d%n", productionLine.getTotalProduced());
        System.out.printf("   ❌ Total Scrapped: %d%n", productionLine.getTotalScrapped());
        System.out.printf("   🏗️ Work In Progress Remaining: %d%n", productionLine.getInProcessCount());

        System.out.println("📋 Completed Products:");
        for (Product p : productionLine.getCompletedProducts()) {
            System.out.printf("   ✔️ %s (%s) | Entry: %s | Exit: %s%n",
                    p.getId(), p.getType(), p.getCreatedAt(), p.getCompletedAt());
        }

        System.out.println("🗑️ Scrapped Products:");
        for (Product p : productionLine.getScrappedProducts()) {
            System.out.printf("   ❌ %s (%s) | Entry: %s%n",
                    p.getId(), p.getType(), p.getCreatedAt());
        }
    }
}
