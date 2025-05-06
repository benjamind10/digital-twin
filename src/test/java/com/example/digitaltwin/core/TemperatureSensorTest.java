package com.example.digitaltwin.core;

import com.example.digitaltwin.event.TemperatureSensorEvent;
import com.example.digitaltwin.event.SensorEvent;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TemperatureSensorTest {

    @Test
    public void testSensorGeneratesValidTemperature() {
        TemperatureSensor sensor = new TemperatureSensor("TempSensor1");
        SensorEvent event = sensor.read();

        assertNotNull(event);
        assertTrue(event instanceof TemperatureSensorEvent);

        double value = ((TemperatureSensorEvent) event).getValue();
        assertTrue(value >= 20.0 && value <= 80.0, "Value out of range: " + value);
    }
}
