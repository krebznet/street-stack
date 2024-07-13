package com.dunkware.street.stream.model.trade;

import java.time.LocalDateTime;
import java.util.UUID;

public class TradeEvent {
    private UUID tradeId;
    private String eventType;
    private String description;
    private LocalDateTime timestamp;

    public TradeEvent(UUID tradeId, String eventType, String description, LocalDateTime timestamp) {
        this.tradeId = tradeId;
        this.eventType = eventType;
        this.description = description;
        this.timestamp = timestamp;
    }

    public UUID getTradeId() {
        return tradeId;
    }

    public String getEventType() {
        return eventType;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}