package com.dunkware.utils.reactive.handlers;
public interface ReactivePublisher<T> {

    /**
     * Method to publish data to the stream.
     *
     * @param data The data to publish.
     */
    void publish(T data);

    /**
     * Method to handle connection closure by the client.
     */
    void onClose();

    /**
     * Method to close the connection.
     */
    void close();
}