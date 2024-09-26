package com.dunkware.street.smart.broker.controllers;

public enum BrokerStatus {
    CONNECTED,
    DISCONNECTED,
    PENDING,
    EXCEPTION,
    CONNECT_LOOP,
    RUNNING
}