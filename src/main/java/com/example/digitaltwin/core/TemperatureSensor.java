package com.example.digitaltwin.core;

import com.example.digitaltwin.event.SensorEvent;
import com.example.digitaltwin.event.TemperatureSensorEvent;

public class TemperatureSensor extends Sensor {

    public TemperatureSensor(String name) {
        super(name);
    }

    @Override
    public SensorEvent read() {
        // Simulate a temperature reading between 20.0 and 80.0
        double value = 20.0 + (60.0 * random.nextDouble());
        return new TemperatureSensorEvent(id, name, value);
    }
}
