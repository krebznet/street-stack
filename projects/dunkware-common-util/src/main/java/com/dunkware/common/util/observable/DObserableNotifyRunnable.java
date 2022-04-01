package com.dunkware.common.util.observable;

public class DObserableNotifyRunnable implements Runnable  {

	private DObservableImpl _obs;
	private Object _arg; 
	
	public DObserableNotifyRunnable(DObservableImpl observable, Object arg) { 
		_obs = observable;
		_arg = arg;
	}
	
	public void run() { 
		_obs.notifyObservers(_obs, _arg);
	}
}
