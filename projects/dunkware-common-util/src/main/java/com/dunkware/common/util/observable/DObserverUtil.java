package com.dunkware.common.util.observable;

public class DObserverUtil {

	public static DObservableTopic getTopic(Object arg) { 
		if(arg == null) { 
			return null;
		}
		if (arg instanceof DObservableTopic) {
			DObservableTopic topic = (DObservableTopic) arg;
			return topic;
		}
		return null;
	}
}
