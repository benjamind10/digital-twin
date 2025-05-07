package com.example.digitaltwin.core;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class Product {
    private final String id;
    private final String type;
    private final Instant createdAt;
    private Instant completedAt;

    private final List<String> route;
    private int currentStep;

    public Product(String type, List<String> route) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.route = route;
        this.currentStep = 0;
        this.createdAt = Instant.now();
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getCompletedAt() {
        return completedAt;
    }

    public boolean isCompleted() {
        return completedAt != null;
    }

    public void markCompleted() {
        this.completedAt = Instant.now();
    }

    public String getCurrentMachineName() {
        return currentStep < route.size() ? route.get(currentStep) : null;
    }

    public void advanceStep() {
        this.currentStep++;
    }

    public boolean isFinished() {
        return currentStep >= route.size();
    }

    @Override
    public String toString() {
        return String.format("Product[id=%s, type=%s, step=%d/%d]", id, type, currentStep, route.size());
    }
}
