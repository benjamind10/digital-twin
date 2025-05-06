package com.example.digitaltwin.core;

import org.junit.jupiter.api.Test;

public class SimulatorSmokeTest {

    @Test
    public void testSimulatorRuns() {
        Simulator sim = new Simulator();
        sim.run();

        // No assertions — this is just a sanity check that the simulation doesn't throw
    }
}
