package com.dunkware.street.stream.model.trade;

public class TradeContextMetrics {
    private int activeTradeCount;
    private double tradedCapital;
    private double realizedGainLoss;
    private double unrealizedGainLoss;
    private int activeOrderCount;

    public int getActiveTradeCount() {
        return activeTradeCount;
    }

    public void setActiveTradeCount(int activeTradeCount) {
        this.activeTradeCount = activeTradeCount;
    }

    public double getTradedCapital() {
        return tradedCapital;
    }

    public void setTradedCapital(double tradedCapital) {
        this.tradedCapital = tradedCapital;
    }

    public double getRealizedGainLoss() {
        return realizedGainLoss;
    }

    public void setRealizedGainLoss(double realizedGainLoss) {
        this.realizedGainLoss = realizedGainLoss;
    }

    public double getUnrealizedGainLoss() {
        return unrealizedGainLoss;
    }

    public void setUnrealizedGainLoss(double unrealizedGainLoss) {
        this.unrealizedGainLoss = unrealizedGainLoss;
    }

    public int getActiveOrderCount() {
        return activeOrderCount;
    }

    public void setActiveOrderCount(int activeOrderCount) {
        this.activeOrderCount = activeOrderCount;
    }
}
