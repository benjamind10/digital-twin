package com.example.digitaltwin.core;

import java.util.UUID;

import com.example.digitaltwin.event.MachineEvent;
import com.example.digitaltwin.event.StateChangeEvent;

public class Machine {
    public enum State {
        IDLE, RUNNING, FAULT, SETUP
    }

    private final String id;
    private final String name;
    private State currentState;

    public Machine(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.currentState = State.IDLE;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public State getCurrentState() {
        return currentState;
    }

    public MachineEvent changeState(State newState) {
        State oldState = this.currentState;
        this.currentState = newState;
        return new StateChangeEvent(id, name, oldState, newState);
    }

    public MachineEvent update() {
        // Placeholder logic: transition to RUNNING after IDLE
        if (currentState == State.IDLE) {
            return changeState(State.RUNNING);
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("Machine[name=%s, id=%s, state=%s]", name, id, currentState);
    }

}
