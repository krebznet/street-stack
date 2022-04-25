package com.dunkware.common.util.events;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.events.anot.ADEventMethod;

public class DEventNode {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private DEventNode parent;
	private ConcurrentHashMap<String, DEventNode> children = new ConcurrentHashMap<String, DEventNode>();

	private DEventTree eventTree;

	private String path;
	private List<AnnotatedEventHandler> eventHandlers = new ArrayList<AnnotatedEventHandler>();
	private Semaphore eventHandlerLock = new Semaphore(1);
	private List<RegisteredEventListener> eventListeners = new ArrayList<RegisteredEventListener>();
	private Semaphore eventListenerLock = new Semaphore(1);

	public DEventNode(DEventNode parent, String path, DEventTree service)   {
		this.parent = parent;
		this.path = path;
		this.eventTree = service;
	}

	public String getPath() {
		return path;
	}

	public DEventNode getParent() {
		return parent;
	}
	
	public DEventNode createChild(String subPath) { 
		DEventNode node = new DEventNode(this, this.path + " " + subPath, eventTree);
		children.put(subPath, node);
		return node;
	}

	public void addEventHandler(Object handler) throws DEventHandlerException {
		AnnotatedEventHandler registered = new AnnotatedEventHandler(handler);
		Runnable runnable = new Runnable() {
			public void run() {
				try {
					eventHandlerLock.acquire();
					eventHandlers.add(registered);
				} catch (Exception e) {
					logger.error("Exception in add event handler ? " + e.toString());
				} finally { 
					eventHandlerLock.release();
				}
			}
		} ;
		this.eventTree.getExecutor().execute(runnable);
	}

	public void removeEventHandler(Object handler) {
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				try {
					eventHandlerLock.acquire();
					AnnotatedEventHandler remove = null;
					for (AnnotatedEventHandler annotatedEventHandler : eventHandlers) {
						if(annotatedEventHandler.source.equals(handler) || annotatedEventHandler.source == handler) {
							remove = annotatedEventHandler;
							break;
						}
					}
					if(remove != null) { 
						eventHandlers.remove(remove);
					}
				} catch (Exception e) {
					logger.error("Exception removing event handler " + e.toString());
				} finally { 
					eventHandlerLock.release();
				}
			}
		};
		eventTree.getExecutor().execute(runnable);
	}

	public void event(DEvent event) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				boolean flag = false;
				try {
					eventHandlerLock.acquire();
					List<AnnotatedEventHandlerMethod> methods = new ArrayList<DEventNode.AnnotatedEventHandlerMethod>();
					for (AnnotatedEventHandler eventHandler : eventHandlers) {
						eventHandler.addMatchingMethodHandlers(event, methods);
					}
					for (AnnotatedEventHandlerMethod method : methods) {
						try {
							if(logger.isTraceEnabled()) { 
								logger.trace("{} Event Handler Method {} on {} Invoke",event.getClass().getName(),method.method.getName(),method.source.getClass().getName());
							}
							method.method.invoke(method.source,event);	
						} catch (Exception e) {
							logger.error("Exception invoking event handler method {} on class {} exception {}",method.method.getName(),method.source.getClass().getName(),e.toString());
						}
						
					}
				} catch (Exception e) {
					logger.error("Error in event node sent " + e.toString());
				} finally { 
					eventHandlerLock.release();
				}
				try {
					
					flag = eventListenerLock.tryAcquire(15, TimeUnit.SECONDS);
					if (!flag) {
						logger.error("Event Handler Lock Acquire Failed In Notify Event");
						return;
					}
					
					for (RegisteredEventListener registeredEventHandler : eventListeners) {
						if (registeredEventHandler.handle(event)) {
							try {
								if (logger.isTraceEnabled()) {
									logger.trace(
											"EventHandler Invocation " + registeredEventHandler.getClass().getName());
								}
								registeredEventHandler.handler.onEvent(event);
							} catch (Exception e) {
								logger.error("Exception calling event handler "
										+ registeredEventHandler.handler.getClass().getName() + " " + e.toString(), e);
							}

						}
					}
				} catch (Exception e) {

				} finally {
					if (flag) {
						eventListenerLock.release();
					}
				}
			}
		};
		geteventTree().getExecutor().execute(runnable);
	}

	public DEventTree geteventTree() {
		return eventTree;
	}

	public void addEventListener(DEventListener listener) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				boolean flag = false;
				try {
					flag = eventListenerLock.tryAcquire(15, TimeUnit.SECONDS);
					if (!flag) {
						logger.error("Event Handler Lock Acquire Failed In AddEventHandler");
						return;
					}
					RegisteredEventListener reg = new RegisteredEventListener();
					reg.handler = listener;
					eventListeners.add(reg);
				} catch (Exception e) {

				} finally {
					if (flag) {
						eventListenerLock.release();
					}
				}
			}
		};
		geteventTree().getExecutor().execute(runnable);
	}

	public void removeEventListener(DEventListener handler) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				boolean flag = false;
				try {
					flag = eventListenerLock.tryAcquire(15, TimeUnit.SECONDS);
					if (!flag) {
						logger.error("Event Handler Lock Acquire Failed In AddEventHandler");
						return;
					}
					RegisteredEventListener found = null;
					for (RegisteredEventListener registeredEventHandler : eventListeners) {
						if (registeredEventHandler.handler == handler) {
							found = registeredEventHandler;
						}
					}
					if (found != null) {
						eventHandlers.remove(found);
					} else {
						logger.warn("Remove Event Handler Not Found " + handler.getClass());
					}
				} catch (Exception e) {

				} finally {
					if (flag) {
						eventListenerLock.release();
					}
				}
			}
		};
		geteventTree().getExecutor().execute(runnable);
	}

	private class RegisteredEventListener {

		public DEventListener handler;

		public boolean handle(DEvent evnent) {
			return true;
		}
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
	}
}
