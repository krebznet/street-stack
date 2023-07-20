package com.dunkware.common.util.events;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.events.anot.ADEventMethod;

public class DEventNode {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private DEventNode parent;
	private ConcurrentHashMap<Class<?>, DEventNode> children = new ConcurrentHashMap<Class<?>, DEventNode>();

	private DEventTree eventTree;
	
	private Object source;

	private List<AnnotatedEventHandler> eventHandlers = new ArrayList<AnnotatedEventHandler>();
	private Semaphore eventHandlerLock = new Semaphore(1);
	

	public DEventNode(DEventNode parent, Object source, DEventTree service)   {
		this.parent = parent;
		this.source = source;
		this.eventTree = service;
	}

	
	
	public DEventNode getParent() {
		return parent;
	}
	
	public Object getSource() { 
		return source; 
	}
	
	/**
	 * Recursively gets all upstream event handlers registered on parent nodes. 
	 * @param hanlders
	 */
	public void getEventHandlerMethods(DEvent event, List<AnnotatedEventHandlerMethod> handlerMethods)  { 
		boolean acquired = false;
		try {
			 acquired = eventHandlerLock.tryAcquire(1, eventTree.getLockTimeout(),eventTree.getLockTimeoutUnit());
			 if(!acquired) { 
				 logger.error("Event Handler Lock Timeout " + source.getClass().getName());;
				 return;
			 }
			for (AnnotatedEventHandler annotatedEventHandler : eventHandlers) {
				annotatedEventHandler.addMatchingMethodHandlers(event, handlerMethods);
			}
		} catch (Exception e) {
			logger.error("Exception getting event handler methods " + e.toString());
		} finally { 
			if(acquired) { 
				eventHandlerLock.release();	
			}
			
		}
		if(parent != null) { 
			parent.getEventHandlerMethods(event, handlerMethods);
		}
	}
	
	
	public DEventNode createChild(Object source) { 
		DEventNode node = new DEventNode(this, source, eventTree);
		children.put(source.getClass(), node);
		return node;
	}

	public void addEventHandler(Object handler) throws DEventHandlerException {
		final AnnotatedEventHandler registered = new AnnotatedEventHandler(handler);
		Runnable runnable = new Runnable() {
			public void run() {
				boolean hasLock = false;
				try {
					hasLock = eventTree.tryAcquire(eventHandlerLock);
					if(!hasLock) { 
						logger.error("Timeout getting event handler lock add event handler " + source.getClass().getName());
						return;
					}
					eventHandlers.add(registered);
				} catch (Exception e) {
					if (e instanceof InterruptedException) { 
						return;
					}
					logger.error("Exception in add event handler ? " + e.toString());
				} finally { 
					if(hasLock) { 
						eventHandlerLock.release();
					}
						
				}
			}
		} ;
		this.eventTree.getExecutor().execute(runnable);
	}

	public void removeEventHandler(Object handler) {
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				boolean hasLock = false; 
				try {
					hasLock = eventTree.tryAcquire(eventHandlerLock);
					if(!hasLock) {
						logger.error("Lock tieout on remove event handler " + source.getClass() + " " + handler.getClass());;
					}
					AnnotatedEventHandler remove = null;
					for (AnnotatedEventHandler annotatedEventHandler : eventHandlers) {
						if(annotatedEventHandler.source.equals(handler) || annotatedEventHandler.source == handler) {
							remove = annotatedEventHandler;
							break;
						}
					}
					if(remove != null) { 
						eventHandlers.remove(remove);
					} else { 
						logger.error("Excent handler " + handler.getClass() + " not found in remove");
					}
				} catch (Exception e) {
					logger.error("Exception removing event handler " + e.toString());
				} finally { 
					if(hasLock) { 
						eventHandlerLock.release();	
					}
					
				}
			}
		};
		eventTree.getExecutor().execute(runnable);
	}
	
	

	/**
	 * Okay so we are running event notifier in its own thread, that thread
	 * will get all matching event handler methods for node handlers and all upstream
	 * node handlers. if a handler is set to async it will run its onw thread if not
	 * in this thread. 
	 * @param event
	 */
	public void event(DEvent event) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
					List<AnnotatedEventHandlerMethod> methods  = new ArrayList<AnnotatedEventHandlerMethod>();
					getEventHandlerMethods(event,methods);
					for (AnnotatedEventHandlerMethod method : methods) {
						
							
							// should invoke this in its own thread I am thinking. 
							if(method.async) { 
								if(logger.isTraceEnabled()) { 
									logger.trace("{} Event Handler Method Async {} on {} Invoke",event.getClass().getName(),method.method.getName(),method.source.getClass().getName());
								}
								EventMethodInvocation invocation = new EventMethodInvocation(method.method, method.source, event);
								geteventTree().getExecutor().execute(invocation);	
							} else { 
								try {
									if(logger.isTraceEnabled()) { 
										logger.trace("{} Event Handler Method {} on {} Invoke",event.getClass().getName(),method.method.getName(),method.source.getClass().getName());
									}
									method.method.invoke(method.source,event);	
								} catch (Exception e) {
									logger.error("Exception invoking event handler evetn node source " + source.getClass().getName() + " source method " + method.method.getName() + " " + method.method.getClass().getName() + e.toString());
								}	
							}
					}	
		}};
		geteventTree().getExecutor().execute(runnable);
	}

	public DEventTree geteventTree() {
		return eventTree;
	}

	

	private class AnnotatedEventHandler {
		public Object source;
		public List<AnnotatedEventHandlerMethod> methods = new ArrayList<AnnotatedEventHandlerMethod>();
		
		public AnnotatedEventHandler(Object source) throws DEventHandlerException { 
			this.source = source;
			for (Method method : source.getClass().getMethods()) {
				ADEventMethod[] anots = method.getAnnotationsByType(ADEventMethod.class);
				if(anots.length > 0) { 
					ADEventMethod anot = anots[0];
					if(method.getParameterTypes().length != 1) { 
						throw new DEventHandlerException("Event Handler method " + method.getName() + " on class " + source.getClass().getName() + " has invalid method signature");
					}
					Class<?> param = method.getParameterTypes()[0];
				
					AnnotatedEventHandlerMethod methodHandler = new AnnotatedEventHandlerMethod();
					methodHandler.method = method;
					methodHandler.expression = anot.expression();
					methodHandler.source = source;
					methodHandler.async = anot.async();
					Class<?> paramClass = method.getParameterTypes()[0];
					if(DEvent.class.isAssignableFrom(paramClass) == false) { 
						throw new DEventHandlerException("method param on event handler method is not type of DEvent " + method.getName() + " " + source.getClass().getName());
					}
					methodHandler.event = paramClass;
					methods.add(methodHandler);
				}
			}
		}
		
		public void addMatchingMethodHandlers(DEvent event, List<AnnotatedEventHandlerMethod> list) { 
			for (AnnotatedEventHandlerMethod method : methods) {
				if(method.event.isInstance(event)) { 
					list.add(method);
				}
			}
		}
	}

	private class AnnotatedEventHandlerMethod {
		public Class<? > event;
		public Method method;
		public String expression;
		public Object source;
		public boolean async = false;
	}
	
	private class EventMethodInvocation implements Runnable { 
		
		private Method method; 
		private Object source; 
		private DEvent event; 
		
		public EventMethodInvocation(Method method, Object source, DEvent event) { 
			this.method = method;
			this.source = source;
			this.event = event; 
		}

		@Override
		public void run() {
			try {
				method.invoke(source, event);
			} catch (Exception e) {
				logger.error("DEvent Method Exception " + method.getDeclaringClass().getName() + " " + method.getName() + " " + e.toString());
			}

		}
		
		
		
	}
}
