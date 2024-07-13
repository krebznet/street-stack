package com.dunkware.street.stream.model.trade;

import java.util.ArrayList;
import java.util.List;


// TradeSet Class
public class TradeSet {
    private List<Trade> trades;
    private TradeContextMetrics metrics;

    public TradeSet() {
        this.trades = new ArrayList<>();
        this.metrics = new TradeContextMetrics();
    }

    public List<Trade> getTrades() {
        return trades;
    }

    public void addTrade(Trade trade) {
        trades.add(trade);
        updateMetrics();
    }

    public TradeContextMetrics getMetrics() {
        return metrics;
    }

    private void updateMetrics() {
        metrics.setActiveTradeCount((int) trades.stream().filter(trade -> trade.getState() == TradeState.OPEN).count());
        metrics.setTradedCapital(trades.stream().mapToDouble(Trade::getTradedCapital).sum());
        metrics.setRealizedGainLoss(trades.stream().mapToDouble(Trade::getRealizedGainLoss).sum());
        metrics.setUnrealizedGainLoss(trades.stream().mapToDouble(Trade::getUnrealizedGainLoss).sum());
        metrics.setActiveOrderCount((int) trades.stream().filter(trade -> trade.getState() == TradeState.OPEN).count());
    }
}