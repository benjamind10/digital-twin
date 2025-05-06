package com.example.digitaltwin.event;

import java.time.Instant;

public class TemperatureSensorEvent implements SensorEvent {
    private final String sourceId;
    private final String sensorName;
    private final double value;
    private final Instant timestamp;

    public TemperatureSensorEvent(String sourceId, String sensorName, double value) {
        this.sourceId = sourceId;
        this.sensorName = sensorName;
        this.value = value;
        this.timestamp = Instant.now();
    }

    @Override
    public String getSourceId() {
        return sourceId;
    }

    @Override
    public Instant getTimestamp() {
        return timestamp;
    }

    @Override
    public String getType() {
        return "TemperatureSensorEvent";
    }

    @Override
    public double getValue() {
        return value;
    }

    public String getSensorName() {
        return sensorName;
    }
}
