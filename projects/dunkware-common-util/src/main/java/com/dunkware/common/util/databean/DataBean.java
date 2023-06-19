package com.dunkware.common.util.databean;

import java.util.Vector;

public abstract class DataBean {

	private Vector<DataBeanObserver> obs = new Vector<DataBeanObserver>();

	public void notifyUpdate() {
		  /*
         * a temporary array buffer, used as a snapshot of the state of
         * current Observers.
         */
        Object[] arrLocal;

        synchronized (this) {
            /* We don't want the Observer doing callbacks into
             * arbitrary code while holding its own Monitor.
             * The code where we extract each Observable from
             * the Vector and store the state of the Observer
             * needs synchronization, but notifying observers
             * does not (should not).  The worst result of any
             * potential race-condition here is that:
             * 1) a newly-added Observer will miss a
             *   notification in progress
             * 2) a recently unregistered Observer will be
             *   wrongly notified when it doesn't care
             */
          
            arrLocal = obs.toArray();
        }

        for (int i = arrLocal.length-1; i>=0; i--)
            ((DataBeanObserver)arrLocal[i]).dataBeanUpdate(this);
	}

	public synchronized void addObserver(DataBeanObserver o) {
		if (!obs.contains(o)) {
            obs.addElement(o);
        }
	}

	public synchronized void removeListener(DataBeanObserver o) {
		 obs.removeElement(o);
	}

}
