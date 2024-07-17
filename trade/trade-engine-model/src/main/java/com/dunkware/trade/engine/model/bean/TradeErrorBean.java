package com.dunkware.trade.engine.model.bean;

import java.time.LocalDateTime;

public class TradeErrorBean {
    private LocalDateTime timestamp;
    private String message;
    private String tradeKey;
    private String orderKey;

    public TradeErrorBean() {
    }

    public TradeErrorBean(LocalDateTime timestamp, String message, String tradeKey, String orderKey) {
        this.timestamp = timestamp;
        this.message = message;
        this.tradeKey = tradeKey;
        this.orderKey = orderKey;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTradeKey() {
        return tradeKey;
    }

    public void setTradeKey(String tradeKey) {
        this.tradeKey = tradeKey;
    }

    public String getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(String orderKey) {
        this.orderKey = orderKey;
    }
}
