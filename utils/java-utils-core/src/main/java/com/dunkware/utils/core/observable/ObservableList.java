package com.dunkware.utils.core.observable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.event.ListEvent;
import ca.odell.glazedlists.event.ListEventListener;

public class ObservableList<T extends Observable<T>> implements ObservableMonitor<T>, ListEventListener<T> {

	private EventList<T> sourceList;
	private boolean running = false; 
    private List<ObservableMonitor<T>> listeners = new ArrayList<ObservableMonitor<T>>();
    private Semaphore listenerLock = new Semaphore(1);
	
    // Constructor accepts an EventList with the same generic type
    public ObservableList(EventList<T> sourceList) {
        this.sourceList = sourceList;
    }
    
    public ObservableList() { 
    	sourceList = GlazedLists.eventList(new ArrayList<T>());
    }

    // Start method
    public void start() {
        // Lock the source list for read operations and process elements
        sourceList.getReadWriteLock().readLock().lock();
        try {
            for (T element : sourceList) {
            	element.addMonitor(this);
            }
        } finally {
        	sourceList.addListEventListener(this);
            sourceList.getReadWriteLock().readLock().unlock();
        }
    }
    
    public void dispose() { 
    	if(running) { 
    		// lock list 
    		// remove bean listeners
    		// remove list listener
    		// 
    	}
    }
    
    EventList<T> getSourceList() { 
    	return sourceList;
    }
    
    public void addListener(ObservableMonitor<T> listener) { 
    	try {
    		this.listenerLock.acquire();
    		this.listeners.add(listener);
		} catch (Exception e) {
			
		} finally { 
			this.listenerLock.release();
		}
    	
    	
    }
    
    
    public void removeListener(ObservableList<T> listener) { 
    	
    }

	@Override
	public void listChanged(ListEvent<T> listChanges) {
		// TODO Auto-generated method stub
		
	}
    
    
    
    


}
