package com.example.digitaltwin.event;

import java.time.Instant;

public interface Event {
    String getSourceId();
    Instant getTimestamp();
    String getType();
}
