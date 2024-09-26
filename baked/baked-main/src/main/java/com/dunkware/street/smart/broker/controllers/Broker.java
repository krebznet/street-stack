package com.dunkware.street.smart.broker.controllers;


import java.time.Duration;

public interface Broker {

    /**
     * @return the unique identifier of the broker.
     */
    String getIdentifier();

    /**
     * @return the current status of the broker (e.g. CONNECTED, DISCONNECTED, etc.)
     */
    BrokerStatus getStatus();

    /**
     * @return the duration since the broker is in its current status.
     */
    Duration getStatusDuration();

    /**
     * @return the type of the broker (e.g. "Stock Broker", "Crypto Broker").
     */
    String getBrokerType();

    /**
     * @return the number of accounts associated with the broker.
     */
    int getAccountCount();

   
    void disconnect() throws Exception;

    // Other methods can be added as needed.
}