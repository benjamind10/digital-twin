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
            int step = product.getStep();

            if (step < machines.size()) {
                Machine machine = machines.get(step);
                machine.update();

                // 💥 Simulate scrap with 10% chance
                boolean isScrapped = isProductScrapped(product);
                if (isScrapped) {
                    System.out.printf("❌ %s scrapped at %s%n", product.getId(), machine.getName());
                    iterator.remove();
                    scrappedProducts.add(product);
                    totalScrapped++;
                    continue;
                }

                product.advanceStep();
                System.out.printf("🔄 %s processed by %s → step %d%n",
                        product.getId(), machine.getName(), product.getStep());
            } else {
                System.out.printf("✅ %s completed processing.%n", product.getId());
                iterator.remove();
                product.markCompleted();
                completedProducts.add(product);
                totalProduced++;
            }
        }

        System.out.println("📊 Current Line Metrics:");
        System.out.printf("   ✔️ Produced: %d%n", totalProduced);
        System.out.printf("   ❌ Scrapped: %d%n", totalScrapped);
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
