package com.example.digitaltwin.event;

import com.example.digitaltwin.core.Machine;

import java.time.Instant;

public class StateChangeEvent implements MachineEvent {
    private final String sourceId;
    private final String machineName;
    private final Machine.State from;
    private final Machine.State to;
    private final Instant timestamp;

    public StateChangeEvent(String sourceId, String machineName, Machine.State from, Machine.State to) {
        this.sourceId = sourceId;
        this.machineName = machineName;
        this.from = from;
        this.to = to;
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
        return "StateChange";
    }

    @Override
    public String getMachineName() {
        return machineName;
    }

    public Machine.State getFrom() {
        return from;
    }

    public Machine.State getTo() {
        return to;
    }
}
