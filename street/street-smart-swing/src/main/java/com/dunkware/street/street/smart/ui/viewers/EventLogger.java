package com.dunkware.street.street.smart.ui.viewers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.dunkware.street.stream.model.trade.TradeEvent;

public class EventLogger {
    private List<TradeEvent> events = new ArrayList<>();

    public void logEvent(UUID tradeId, String eventType, String description) {
        TradeEvent event = new TradeEvent(tradeId, eventType, description, LocalDateTime.now());
        events.add(event);
    }

    public List<TradeEvent> getEventsByTradeId(UUID tradeId) {
        return events.stream()
                .filter(event -> event.getTradeId().equals(tradeId))
                .collect(Collectors.toList());
    }

    public List<TradeEvent> getAllEvents() {
        return new ArrayList<>(events);
    }
}