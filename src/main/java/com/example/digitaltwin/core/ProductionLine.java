package com.example.digitaltwin.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ProductionLine {

    private final String name;
    private final List<Machine> machines;
    private final List<Product> products;
    private final List<Product> completedProducts;
    private final List<Product> scrappedProducts;
    private final Random random;

    private int totalProduced = 0;
    private int totalScrapped = 0;

    public ProductionLine(String name) {
        this.name = name;
        this.machines = new ArrayList<>();
        this.products = new ArrayList<>();
        this.completedProducts = new ArrayList<>();
        this.scrappedProducts = new ArrayList<>();
        this.random = new Random();
    }

    public void addMachine(Machine machine) {
        machines.add(machine);
        System.out.printf("🏭 %s added to %s%n", machine, name);
    }

    public void feedProduct(Product product) {
        products.add(product);
        System.out.printf("📥 %s added to %s%n", product, name);
    }

    public void processStep() {
        System.out.printf("📦 %s running process step...%n", name);

        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();

            if (!product.isFinished()) {
                String machineName = product.getCurrentMachineName();
                Machine machine = findMachineByName(machineName);
                machine.update();

                // Simulate scrap with reason
                String scrapReason = getScrapReason(product);
                if (scrapReason != null) {
                    System.out.printf("❌ %s scrapped at %s — reason: %s%n", product.getId(), machine.getName(), scrapReason);
                    scrappedProducts.add(product);
                    iterator.remove();
                    totalScrapped++;
                    continue;
                }

                product.advanceStep();
                System.out.printf("🔄 %s processed by %s → next step: %s%n",
                        product.getId(), machine.getName(), product.getCurrentMachineName());
            } else {
                System.out.printf("✅ %s completed processing.%n", product.getId());
                product.markCompleted();
                completedProducts.add(product);
                iterator.remove();
                totalProduced++;
            }
        }

        System.out.println("📊 Current Line Metrics:");
        System.out.printf("   ✔️ Produced: %d%n", totalProduced);
        System.out.printf("   ❌ Scrapped: %d%n", totalScrapped);
    }

// Extracted method for scrap reason (null = not scrapped)
    protected String getScrapReason(Product product) {
        if (random.nextDouble() < 0.10) {
            return "RandomFailure";
        }
        return null;
    }

    private Machine findMachineByName(String name) {
        return machines.stream()
                .filter(m -> m.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Machine not found: " + name));
    }

    public boolean isProcessingComplete() {
        return products.isEmpty();
    }

    public int getTotalProduced() {
        return totalProduced;
    }

    public int getTotalScrapped() {
        return totalScrapped;
    }

    public int getInProcessCount() {
        return products.size();
    }

    public List<Product> getCompletedProducts() {
        return completedProducts;
    }

    public List<Product> getScrappedProducts() {
        return scrappedProducts;
    }

    public String getName() {
        return name;
    }

    protected boolean isProductScrapped(Product product) {
        return random.nextDouble() < 0.10;
    }
}
