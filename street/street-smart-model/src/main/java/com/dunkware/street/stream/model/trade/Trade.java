package com.dunkware.street.stream.model.trade;

import java.util.UUID;


public class Trade {
    private UUID id;
    private String symbol;
    private TradeState state;
    private double entryPrice;
    private double exitPrice;
    private int volume;

    public Trade(UUID id, String symbol, TradeState state, double entryPrice, double exitPrice, int volume) {
        this.id = id;
        this.symbol = symbol;
        this.state = state;
        this.entryPrice = entryPrice;
        this.exitPrice = exitPrice;
        this.volume = volume;
    }

    public UUID getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public TradeState getState() {
        return state;
    }

    public double getEntryPrice() {
        return entryPrice;
    }

    public double getExitPrice() {
        return exitPrice;
    }

    public int getVolume() {
        return volume;
    }

    public double getTradedCapital() {
        return entryPrice * volume;
    }

    public double getRealizedGainLoss() {
        return (exitPrice - entryPrice) * volume;
    }

    public double getUnrealizedGainLoss() {
        // Assuming current price is 0 for simplicity; this should be fetched from market data
        return (0 - entryPrice) * volume;
    }

    public int getActiveOrderCount() {
        // Example: returning 1 for simplicity
        return 1;
    }
}