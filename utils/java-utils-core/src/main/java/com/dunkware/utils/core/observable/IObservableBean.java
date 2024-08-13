package com.dunkware.utils.core.observable;

public interface IObservableBean {

	public void notifyUpdate();
	
	public void addObserver(ObservableBeanListener o);
	

	public  void removeListener(ObservableBeanListener o);
	
}
