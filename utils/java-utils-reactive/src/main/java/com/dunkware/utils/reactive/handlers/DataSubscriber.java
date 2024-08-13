package com.dunkware.utils.reactive.handlers;

public interface DataSubscriber<T> {
    /**
     * Called when a new data item is received from the stream.
     *
     * @param data The data item received.
     */
    void onNext(T data);

    /**
     * Called when an error occurs in the data stream.
     *
     * @param throwable The exception representing the error.
     */
    void onError(Throwable throwable);

    /**
     * Called when the server closes the connection.
     */
    void onServerClose();

    /**
     * Called when the client wants to close the connection.
     */
    void closeConnection();
}