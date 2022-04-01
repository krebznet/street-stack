package com.dunkware.common.util.observable;

import java.util.concurrent.Executor;

import com.dunkware.common.util.executor.DExecutor;

public interface DObservable {

	 public void addObserver(DObserver o);
	 
	 public void addObserver(DObserver o, DObservableTopic... topics);
	 
	 public void deleteObserver(DObserver o);

	 public boolean hasChanged();
	 
	 public int countObservers();
	 
	 public DObserver[] getObservers();

	 public boolean asyncNotifyEnabled();
	 
	 public void enableAsyncNotify(Executor executor);
	 
	 public void enableAsyncNotify(DExecutor executor); 
}
