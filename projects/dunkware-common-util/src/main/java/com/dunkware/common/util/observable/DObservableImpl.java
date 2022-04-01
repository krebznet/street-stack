package com.dunkware.common.util.observable;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.common.util.uuid.DUUID;

public abstract class DObservableImpl implements DObservable {
	
	  private boolean changed = false;
	    //private Vector<KObserver> obs;
	    
	    private Vector<RegisteredObserver> obs = new Vector<RegisteredObserver>();
	    
	    private Executor executor = null;
	    
	    private DExecutor kexecutor = null;
	    
	    private boolean asyncNotifyEnabled = false; 
	    
	    private Logger logger = LoggerFactory.getLogger(getClass());

	    /** Construct an Observable with zero Observers. */

	    public DObservableImpl() {
	        
	    }

	    /**
	     * Adds an observer to the set of observers for this object, provided
	     * that it is not the same as some observer already in the set.
	     * The order in which notifications will be delivered to multiple
	     * observers is not specified. See the class comment.
	     *
	     * @param   o   an observer to be added.
	     * @throws NullPointerException   if the parameter o is null.
	     */
	    @SuppressWarnings("unlikely-arg-type")
		public synchronized void addObserver(DObserver o) {
	        if (o == null)
	            throw new NullPointerException();
	        if (!obs.contains(o)) {
	            obs.addElement(new RegisteredObserver(o));
	        }
	    }
	    
	    
	    
	 
	    @Override
		public DObserver[] getObservers() {
	    	DObserver[] retValue = new DObserver[obs.size()];
	    	int i = 0;
	    	for (RegisteredObserver kObserver : this.obs) {
	    		retValue[i] = (DObserver)kObserver.observer;
	    		i++;
			}
	    	return retValue;
			//return obs.toArray(new KObserver[obs.size()]);
		}

		@Override
		public void addObserver(DObserver o, DObservableTopic... topics) {
	    	  if (o == null)
		            throw new NullPointerException();
		       	obs.addElement(new RegisteredObserver(o,topics));
		       
		}

		/**
	     * Deletes an observer from the set of observers of this object.
	     * Passing <CODE>null</CODE> to this method will have no effect.
	     * @param   o   the observer to be deleted.
	     */
	    public synchronized void deleteObserver(DObserver o) {
	    	RegisteredObserver registered = null;
	    	for (RegisteredObserver registeredObserver : obs) {
				if(registeredObserver.observer.equals(o)) { 
					registered = registeredObserver;
					break;
				}
			}
	    	if(registered != null) { 
	    		obs.remove(registered);
	    	}
	    
	    }

	    /**
	     * If this object has changed, as indicated by the
	     * <code>hasChanged</code> method, then notify all of its observers
	     * and then call the <code>clearChanged</code> method to
	     * indicate that this object has no longer changed.
	     * <p>
	     * Each observer has its <code>update</code> method called with two
	     * arguments: this observable object and <code>null</code>. In other
	     * words, this method is equivalent to:
	     * <blockquote><tt>
	     * notifyObservers(null)</tt></blockquote>
	     *
	     * @see     java.util.Observable#clearChanged()
	     * @see     java.util.Observable#hasChanged()
	     * @see     java.util.Observer#update(java.util.Observable, java.lang.Object)
	     */
	    public void notifyObservers() {
	        notifyObservers(null);
	    }

	    
	  
	    /**
	     * If this object has changed, as indicated by the
	     * <code>hasChanged</code> method, then notify all of its observers
	     * and then call the <code>clearChanged</code> method to indicate
	     * that this object has no longer changed.
	     * <p>
	     * Each observer has its <code>update</code> method called with two
	     * arguments: this observable object and the <code>arg</code> argument.
	     *
	     * @param   arg   any object.
	     * @see     java.util.Observable#clearChanged()
	     * @see     java.util.Observable#hasChanged()
	     * @see     java.util.Observer#update(java.util.Observable, java.lang.Object)
	     */
	    public void notifyObservers(Object arg) {
	       notifyObservers(this, arg);
	    }
	    
	    
	    
	    public void notifyObservers(DObservable observable, Object arg) { 
	    	String notifyId = DUUID.randomUUID(5);
	    	if(debug()) { 
	    		if(arg != null) { 
	    			if (arg instanceof DObservableTopic) {
						DObservableTopic topic = (DObservableTopic) arg;
						logger.debug(notifyId + " NotifyObservers " + observable.getClass().getName() + " topic " + topic.getName());		
					}
	    		}
	    		
	    	}
	    	if(asyncNotifyEnabled) { 
	    		Runnable notifier = new Runnable() {
					
					@Override
					public void run() {
						Object[] arrLocal;

				        synchronized (this) {
				            arrLocal = obs.toArray();
				            clearChanged();
				        }
				        DObservableTopic topic = null;
				        if(arg != null) {
				        	if (arg instanceof DObservableTopic) {
								 topic = (DObservableTopic) arg;
							}	
				        }
				        
				        for (int i = arrLocal.length-1; i>=0; i--) {
				        	RegisteredObserver obs = (RegisteredObserver)arrLocal[i];
					        if(topic == null) { 
					        	obs.observer.update(observable, arg);
					        } else { 
					        	if(obs.notify(topic)) { 
					        		if(debug()) { 
					        			logger.debug(notifyId + " " +  observable.getClass().getName() + " topic " + topic.getName());
					        		}
					        		obs.observer.update(observable, arg);
					        	}
					        }
				        }
					}
				};
				if(executor == null)
					kexecutor.execute(notifier);
				else
					executor.execute(notifier);
				return;
	    	}
	    	
	        Object[] arrLocal;

	        synchronized (this) {
	            arrLocal = obs.toArray();
	            clearChanged();
	        }
	        DObservableTopic topic = null;
	        if(arg != null) {
	        	if (arg instanceof DObservableTopic) {
					 topic = (DObservableTopic) arg;
				}	
	        }
	        
	        for (int i = arrLocal.length-1; i>=0; i--) {
	        	RegisteredObserver obs = (RegisteredObserver)arrLocal[i];
		        if(topic == null) { 
		        	obs.observer.update(observable, arg);
		        } else { 
		        	if(obs.notify(topic)) { 
		        		obs.observer.update(observable, arg);
		        	}
		        }
	        }
	    }

	    
	    /**
	     * Clears the observer list so that this object no longer has any observers.
	     */
	    public synchronized void deleteObservers() {
	        obs.removeAllElements();
	    }

	    /**
	     * Marks this <tt>Observable</tt> object as having been changed; the
	     * <tt>hasChanged</tt> method will now return <tt>true</tt>.
	     */
	    protected synchronized void setChanged() {
	        changed = true;
	    }

	    /**
	     * Indicates that this object has no longer changed, or that it has
	     * already notified all of its observers of its most recent change,
	     * so that the <tt>hasChanged</tt> method will now return <tt>false</tt>.
	     * This method is called automatically by the
	     * <code>notifyObservers</code> methods.
	     *
	     * @see     java.util.Observable#notifyObservers()
	     * @see     java.util.Observable#notifyObservers(java.lang.Object)
	     */
	    protected synchronized void clearChanged() {
	        changed = false;
	    }

	    /**
	     * Tests if this object has changed.
	     *
	     * @return  <code>true</code> if and only if the <code>setChanged</code>
	     *          method has been called more recently than the
	     *          <code>clearChanged</code> method on this object;
	     *          <code>false</code> otherwise.
	     * @see     java.util.Observable#clearChanged()
	     * @see     java.util.Observable#setChanged()
	     */
	    public synchronized boolean hasChanged() {
	        return changed;
	    }

	    /**
	     * Returns the number of observers of this <tt>Observable</tt> object.
	     *
	     * @return  the number of observers of this object.
	     */
	    public synchronized int countObservers() {
	        return obs.size();
	    }
	    
	    
	    
	    @Override
		public boolean asyncNotifyEnabled() {
			return asyncNotifyEnabled;
		}

		@Override
		public void enableAsyncNotify(Executor executor) {
			this.executor = executor;
			asyncNotifyEnabled = true;
		}

		


		@Override
		public void enableAsyncNotify(DExecutor executor) {
			asyncNotifyEnabled = true;
			this.kexecutor = executor;
		}




		private class RegisteredObserver { 
	     	public DObserver observer;
	    	public Map<DObservableTopic,Object> topicMap = null; 
	    	
	    	public RegisteredObserver(DObserver obs) {
	    			observer = obs;
	    	}
	   
	     	public RegisteredObserver(DObserver obs, DObservableTopic[] topics) {
	     		topicMap = new HashMap<DObservableTopic,Object>();
	     		observer = obs;
    			for (DObservableTopic kObservableTopic : topics) {
					topicMap.put(kObservableTopic,kObservableTopic);
				}
	     	}
	     	
	     	public boolean notify(DObservableTopic topic) { 
	     		// always return true if this observer does not have 
	     		// any topic filters
	     		if(topicMap == null) {
	     			return true;
	     		}
	     		// return true if subscribed to topic
	     		if(topicMap.containsKey(topic)) {
	     			return true;
	     		}
	     		// else return fasle
	     		return false;
	     	}
	    }
		
		private boolean debug() { 
			return logger.isDebugEnabled();
		}
		
		private boolean trace() { 
			return logger.isTraceEnabled();
		}
	    

}
