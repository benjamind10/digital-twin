package com.example.digitaltwin.core;

import com.example.digitaltwin.event.MachineEvent;
import com.example.digitaltwin.event.StateChangeEvent;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MachineTest {

    @Test
    public void testInitialStateIsIdle() {
        Machine machine = new Machine("TestMachine");
        assertEquals(Machine.State.IDLE, machine.getCurrentState());
    }

    @Test
    public void testStateChange() {
        Machine machine = new Machine("TestMachine");
        MachineEvent event = machine.changeState(Machine.State.RUNNING);

        assertNotNull(event);
        assertTrue(event instanceof StateChangeEvent);

        StateChangeEvent stateEvent = (StateChangeEvent) event;
        assertEquals(Machine.State.IDLE, stateEvent.getFrom());
        assertEquals(Machine.State.RUNNING, stateEvent.getTo());
    }

    @Test
    public void testUpdateTriggersStateChange() {
        Machine machine = new Machine("TestMachine");
        MachineEvent event = machine.update();

        assertNotNull(event);
        assertEquals(Machine.State.RUNNING, machine.getCurrentState());
    }
}
