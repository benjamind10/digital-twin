package com.example.digitaltwin.core;

import com.example.digitaltwin.event.SensorEvent;

import java.util.Random;
import java.util.UUID;

public abstract class Sensor {
    protected final String id;
    protected final String name;
    protected final Random random;

    public Sensor(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.random = new Random();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Each sensor generates its own kind of event
    public abstract SensorEvent read();
}
