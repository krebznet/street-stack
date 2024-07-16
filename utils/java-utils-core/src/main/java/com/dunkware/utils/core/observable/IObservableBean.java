package com.dunkware.utils.core.observable;

public interface IObservableBean {

	public void notifyUpdate();
	
	public void addObserver(ObservableListener o);
	

	public  void removeListener(ObservableListener o);
	
}
