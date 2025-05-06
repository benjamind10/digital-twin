package com.example.digitaltwin.core;

public class Simulator {

    private final ProductionLine productionLine;

    public Simulator() {
        this.productionLine = new ProductionLine("MainLine");
        productionLine.addMachine(new Machine("Cutter"));
        productionLine.addMachine(new Machine("Welder"));
        productionLine.addMachine(new Machine("Assembler"));

        productionLine.feedProduct(new Product("Widget"));
        productionLine.feedProduct(new Product("Widget"));
        productionLine.feedProduct(new Product("Gadget"));
        productionLine.feedProduct(new Product("Gadget"));
    }

    public void run() {
        System.out.println("🔁 Starting Production Line Simulation...\n");

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

        System.out.println("✅ Simulation Complete.\n");

        System.out.printf("🏁 Final Metrics for %s%n", productionLine.getName());
        System.out.printf("   ✔️ Total Produced: %d%n", productionLine.getTotalProduced());
        System.out.printf("   ❌ Total Scrapped: %d%n", productionLine.getTotalScrapped());
        System.out.printf("   🏗️ Work In Progress Remaining: %d%n", productionLine.getInProcessCount());

        System.out.println("\n📋 Completed Products:");
        for (Product p : productionLine.getCompletedProducts()) {
            System.out.printf("   ✔️ %s (%s) | Entry: %s | Exit: %s%n",
                    p.getId(), p.getType(), p.getCreatedAt(), p.getCompletedAt());
        }

        System.out.println("\n🗑️ Scrapped Products:");
        for (Product p : productionLine.getScrappedProducts()) {
            System.out.printf("   ❌ %s (%s) | Entry: %s%n",
                    p.getId(), p.getType(), p.getCreatedAt());
        }
    }
}
