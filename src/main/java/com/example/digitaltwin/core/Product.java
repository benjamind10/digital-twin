package com.example.digitaltwin.core;

import java.time.Instant;
import java.util.UUID;

public class Product {
    private final String id;
    private final String type;
    private int step;
    private final Instant createdAt;
    private Instant completedAt;

    public Product(String type) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.step = 0;
        this.createdAt = Instant.now();
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getStep() {
        return step;
    }

    public void advanceStep() {
        this.step++;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getCompletedAt() {
        return completedAt;
    }

    public void markCompleted() {
        this.completedAt = Instant.now();
    }

    public boolean isCompleted() {
        return completedAt != null;
    }

    @Override
    public String toString() {
        return String.format("Product[id=%s, type=%s, step=%d]", id, type, step);
    }
}
