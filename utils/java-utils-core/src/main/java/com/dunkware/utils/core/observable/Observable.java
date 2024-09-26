package com.dunkware.utils.core.observable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Vector;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Observable<T extends Observable<T>> {

    @JsonIgnore
    private final Vector<Observer<T>> observers = new Vector<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    // Add an observer to the list
    public void addListener(Observer<T> monitor) {
        lock.writeLock().lock();
        try {
            if (!observers.contains(monitor)) {
                observers.add(monitor);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    // Remove an observer from the list
    public void removeListener(Observer<T> monitor) {
        lock.writeLock().lock();
        try {
            observers.remove(monitor);
        } finally {
            lock.writeLock().unlock();
        }
    }

    // Notify all observers of a general update
    protected void update() {
        Object[] localObservers;

        lock.readLock().lock();
        try {
            localObservers = observers.toArray();
        } finally {
            lock.readLock().unlock();
        }

        for (Object observer : localObservers) {
            @SuppressWarnings("unchecked")
            Observer<T> monitor = (Observer<T>) observer;
            monitor.beanUpdate((T) this);
        }
    }

    // Notify observers of a specific property change
    protected void propertyUpdate(String property, Object oldValue, Object newValue) {
        Object[] localObservers;

        lock.readLock().lock();
        try {
            localObservers = observers.toArray();
        } finally {
            lock.readLock().unlock();
        }

        for (Object observer : localObservers) {
            @SuppressWarnings("unchecked")
            Observer<T> monitor = (Observer<T>) observer;
            monitor.propertyUpdate((T) this, property, newValue);
        }
    }
}