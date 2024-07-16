package com.dunkware.utils.core.events;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.utils.core.eventlogger.EventLogger;
import com.dunkware.utils.core.events.anot.ADunkEventHandler;
import com.dunkware.utils.core.helpers.DunkReflection;
//TODO: AVINASHANV-20 Event Node 
/**
 * this is a awesome event notification framework and what happens is you start with an EventTree
 * and it has a root node. child nodes can be created from parent nodes and a event node accepts
 * an object with annotations to listen to events based on the class type. the event firing of 
 * child nodes are dispatched to all parent nodes so that listeners on parenet nodes get all
 * child events. an example of this is a trade bot engine, a trade bot has an event node
 * and the root is a trade session, when new trades are created a trade creates a child event node
 * so you can register event listeners on a trade, a trade creates order objects and they also create
 * a child event node from the trade, so a listener on a trade can listen to all ordreson the trade as well. 
 * this event framework is used in many places, we have EventObjects that are defined. an annotated
 * methid with @Eve
 */

public class DunkEventNode {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private DunkEventNode parent;
	private ConcurrentHashMap<Class<?>, DunkEventNode> children = new ConcurrentHashMap<Class<?>, DunkEventNode>();

	private DunkEventTree eventTree;
	
	private Object source;

	private List<AnnotateDunkEventHandler> eventHandlers = new ArrayList<AnnotateDunkEventHandler>();
	private Semaphore eventHandlerLock = new Semaphore(1);
	
	private EventLogger eventLogger; 
	

	public DunkEventNode(DunkEventNode parent, Object source, DunkEventTree service)   {
		this.parent = parent;
		this.source = source;
		this.eventTree = service;
		
	//	if(parent != null) { 
	//		EventLoggerBuilder b = new EventLoggerBuilder(parent.getEventLogger(), source.getClass().getName(), source.getClass().getName(),null,null);
	//		this.eventLogger = b.build();
	//	}
	//}//
		}
	public EventLogger getEventLogger() { 
		return eventLogger;
	}
	
	public DunkEventNode getParent() {
		return parent;
	}
	
	public Object getSource() { 
		return source; 
	}
	
	
	
	/**
	 * Recursively gets all upstream event handlers registered on parent nodes. 
	 * @param hanlders
	 */
	public void getEventHandlerMethods(DunkEvent event, List<AnnotateDunkEventHandlerMethod> handlerMethods)  { 
		logger.debug("get event handler methods on event " + event.getClass().getName() + " source " + source.getClass().getName());
		boolean acquired = false;
		try {
			 acquired = eventHandlerLock.tryAcquire(1, eventTree.getLockTimeout(),eventTree.getLockTimeoutUnit());
			 if(!acquired) { 
				 logger.error("Event Handler Lock Timeout " + source.getClass().getName());;
				 return;
			 }
			for (AnnotateDunkEventHandler annotateDunkEventHandler : eventHandlers) {
				annotateDunkEventHandler.addMatchingMethodHandlers(event, handlerMethods);
			}
		} catch (Exception e) {
			logger.error("Exception getting event handler methods " + e.toString());
		} finally { 
			if(acquired) { 
				eventHandlerLock.release();	
			}
			
		}
		if(parent != null) { 
			logger.debug("get event handler methods on parent source " + parent.getSource().getClass().getName());
			parent.getEventHandlerMethods(event, handlerMethods);
		}
	}
	
	
	public DunkEventNode createChild(Object source) { 
		DunkEventNode node = new DunkEventNode(this, source, eventTree);
		children.put(source.getClass(), node);
		return node;
	}

	public void adDunkEventHandler(Object handler) throws DunkEventException {
		final AnnotateDunkEventHandler registered = new AnnotateDunkEventHandler(handler);
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
					AnnotateDunkEventHandler remove = null;
					for (AnnotateDunkEventHandler annotateDunkEventHandler : eventHandlers) {
						if(annotateDunkEventHandler.source.equals(handler) || annotateDunkEventHandler.source == handler) {
							remove = annotateDunkEventHandler;
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
	public void event(final DunkEvent event) {
		
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
					List<AnnotateDunkEventHandlerMethod> methods  = new ArrayList<AnnotateDunkEventHandlerMethod>();
					getEventHandlerMethods(event,methods);
					for (AnnotateDunkEventHandlerMethod method : methods) {
						
							
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
									try {
									
										method.method.invoke(method.source,event);										
									} catch (Exception e) {
										e.printStackTrace();
									}
	
								} catch (Exception e) {
									logger.error("Exception invoking event handler evetn node source " + source.getClass().getName() + " source method " + method.method.getName() + " " + method.method.getClass().getName() + e.toString());
								}	
							}
					}	
		}};
		geteventTree().getExecutor().execute(runnable);
	}

	public DunkEventTree geteventTree() {
		return eventTree;
	}

	

	private class AnnotateDunkEventHandler {
		public Object source;
		public List<AnnotateDunkEventHandlerMethod> methods = new ArrayList<AnnotateDunkEventHandlerMethod>();
		
		public AnnotateDunkEventHandler(Object source) throws DunkEventException { 
			this.source = source;
			for (Method method : source.getClass().getMethods()) {
				ADunkEventHandler[] anots = method.getAnnotationsByType(ADunkEventHandler.class);
				if(anots.length > 0) { 
					ADunkEventHandler anot = anots[0];
					if(method.getParameterTypes().length != 1) { 
						throw new DunkEventException("Event Handler method " + method.getName() + " on class " + source.getClass().getName() + " has invalid method signature");
					}
					Class<?> param = method.getParameterTypes()[0];
				
					AnnotateDunkEventHandlerMethod methodHandler = new AnnotateDunkEventHandlerMethod();
					methodHandler.method = method;
					methodHandler.expression = anot.expression();
					methodHandler.source = source;
					methodHandler.async = anot.async();
					Class<?> paramClass = method.getParameterTypes()[0];
					if(DunkEvent.class.isAssignableFrom(paramClass) == false) { 
						throw new DunkEventException("method param on event handler method is not type of DunkEvent " + method.getName() + " " + source.getClass().getName());
					}
					methodHandler.event = paramClass;
					methods.add(methodHandler);
				}
			}
		}
		
		public void addMatchingMethodHandlers(DunkEvent event, List<AnnotateDunkEventHandlerMethod> list) { 
			for (AnnotateDunkEventHandlerMethod method : methods) {
				if(method.method.getParameterTypes().length > 0) {
					if(method.method.getName().equals("sessionEvent")) { 
						System.out.println("Stop here");
					}
					Class clazz = method.method.getParameterTypes()[0];
					if(DunkReflection.isAssignableFrom(clazz, event.getClass())) { 
						list.add(method);
					}
				}

			}
		}
	}

	private class AnnotateDunkEventHandlerMethod {
		public Class<? > event;
		public Method method;
		public String expression;
		public Object source;
		public boolean async = false;
	}
	
	private class EventMethodInvocation implements Runnable { 
		
		private Method method; 
		private Object source; 
		private DunkEvent event; 
		
		public EventMethodInvocation(Method method, Object source, DunkEvent event) { 
			this.method = method;
			this.source = source;
			this.event = event; 
		}

		@Override
		public void run() {
			try {
				method.invoke(source, event);
			} catch (Exception e) {
				logger.error("DunkEvent Method Exception " + method.getDeclaringClass().getName() + " " + method.getName() + " " + e.toString());
			}

		}
		
		
		
	}
}
