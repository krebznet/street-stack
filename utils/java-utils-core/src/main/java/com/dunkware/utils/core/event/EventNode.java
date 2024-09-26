package com.dunkware.utils.core.event;

import com.dunkware.utils.core.event.anot.AEventHandler;
import com.dunkware.utils.core.helpers.DunkReflection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;


/**
 * EventNode in the event tree, any parent nodes will be notified of events on this node
 */
public class EventNode {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private EventNode parent;
    private ConcurrentHashMap<Class<?>, EventNode> children = new ConcurrentHashMap<>();
    private EventTree eventTree;
    private Object source;
    private final List<RegisteredHandler> eventHandlers = new ArrayList<>();
    private final Semaphore eventHandlerLock = new Semaphore(1);

    public EventNode(EventNode parent, Object source, EventTree service) {
        this.parent = parent;
        this.source = source;
        this.eventTree = service;
    }

    public EventNode getParent() {
        return parent;
    }

    public void getEventHandlerMethods(Object event, List<RegisteredHandler.EventHandlerMethod> handlerMethods) {
        if (logger.isDebugEnabled()) {
            logger.debug("Event received: {} | Source: {}", event.getClass().getName(), source.getClass().getName());
        }

        boolean acquired = false;
        try {
            acquired = eventHandlerLock.tryAcquire(1, eventTree.getLockTimeout(), eventTree.getLockTimeoutUnit());
            if (!acquired) {
                logger.error("Event Handler Lock Timeout for source {}", source.getClass().getName());
                return;
            }

            // Iterate over all registered handlers for the current node
            for (RegisteredHandler registeredHandler : eventHandlers) {
                registeredHandler.addMatchingMethodHandlers(event, handlerMethods);
            }
        } catch (Exception e) {
            logger.error("Exception getting event handler methods: {}", e.toString());
        } finally {
            if (acquired) {
                eventHandlerLock.release();
            }
        }

    }

    public Object getSource() {
        return source;
    }

    public EventNode createChild(Object source) {
        EventNode node = new EventNode(this, source, eventTree);
        children.put(source.getClass(), node);
        return node;
    }

    public void addEventHandler(Object handler) throws EventException {
        final RegisteredHandler registered = new RegisteredHandler(handler);
        Runnable runnable = new Runnable() {
            public void run() {
                boolean hasLock = false;
                try {
                    hasLock = eventTree.tryAcquire(eventHandlerLock);
                    if (!hasLock) {
                        logger.error("Timeout getting event handler lock when adding handler for {}", source.getClass().getName());
                        return;
                    }
                    eventHandlers.add(registered);
                } catch (Exception e) {
                    if (e instanceof InterruptedException) {
                        return;
                    }
                    logger.error("Exception adding event handler: {}", e.toString());
                } finally {
                    if (hasLock) {
                        eventHandlerLock.release();
                    }
                }
            }
        };
        this.eventTree.getExecutor().execute(runnable);
    }

    public void removeEventHandler(Object handler) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                boolean hasLock = false;
                try {
                    hasLock = eventTree.tryAcquire(eventHandlerLock);
                    if (!hasLock) {
                        logger.error("Lock timeout when removing event handler for source {}", source.getClass().getName());
                    }

                    RegisteredHandler remove = null;
                    for (RegisteredHandler registeredHandler : eventHandlers) {
                        if (registeredHandler.source.equals(handler) || registeredHandler.source.getClass().getName().equals(handler.getClass().getName())) {
                            remove = registeredHandler;
                            break;
                        }
                    }

                    if (remove != null) {
                        eventHandlers.remove(remove);
                    } else {
                        logger.error("Event handler {} not found during removal", handler.getClass().getName());
                    }
                } catch (Exception e) {
                    logger.error("Exception removing event handler: {}", e.toString());
                } finally {
                    if (hasLock) {
                        eventHandlerLock.release();
                    }
                }
            }
        };
        eventTree.getExecutor().execute(runnable);
    }

    /**
     * Triggers an event, invoking all matching event handlers. Event handlers
     * can be run asynchronously if marked with {@code @AEventHandler(async = true)}.
     *
     * @param event the event object
     */
    public void event(final Object event) {
        List<RegisteredHandler.EventHandlerMethod> methods = new ArrayList<>();
        getEventHandlerMethods(event, methods);

        for (RegisteredHandler.EventHandlerMethod method : methods) {
            if (logger.isDebugEnabled()) {
                logger.debug("Invoking handler method {} on event {}", method.method.getName(), event.getClass().getName());
            }

            // Invoke method asynchronously if marked as async
            if (method.async) {
                if (logger.isTraceEnabled()) {
                    logger.trace("Event Handler Method Async: {} on {}", method.method.getName(), method.source.getClass().getName());
                }
                EventMethodInvocation invocation = new EventMethodInvocation(method.method, method.source, event);
                geteventTree().getExecutor().execute(invocation);
            } else {
                try {
                    if (logger.isTraceEnabled()) {
                        logger.trace("Invoking handler method {} on event {}", method.method.getName(), event.getClass().getName());
                    }
                    method.method.invoke(method.source, event);
                } catch (Exception e) {
                    logger.error("Exception invoking event handler method {} on event {}: {}", method.method.getName(), event.getClass().getName(), e.toString());
                }
            }
        }

        // Propagate the event to the parent node
        if (getParent() != null) {
            getParent().event(event);
        }
    }

    private class EventMethodInvocation implements Runnable {
        private Method method;
        private Object source;
        private Object event;

        public EventMethodInvocation(Method method, Object source, Object event) {
            this.method = method;
            this.source = source;
            this.event = event;
        }

        @Override
        public void run() {
            try {
                method.invoke(source, event);
            } catch (Exception e) {
                logger.error("DunkEvent Method Exception {} {}: {}", method.getDeclaringClass().getName(), method.getName(), e.toString());
            }
        }
    }

    public EventTree geteventTree() {
        return eventTree;
    }

    private class RegisteredHandler {
        public Object source;
        public List<EventHandlerMethod> methods = new ArrayList<>();

        public RegisteredHandler(Object source) {
            this.source = source;
            for (Method method : source.getClass().getMethods()) {
                AEventHandler[] annotations = method.getAnnotationsByType(AEventHandler.class);
                if (annotations.length > 0) {
                    AEventHandler annotation = annotations[0];
                    if (method.getParameterTypes().length != 1) {
                        logger.error("Registered handler {} method {} does not have exactly 1 parameter", source.getClass().getName(), method.getName());
                        continue;
                    }

                    Class<?> paramClass = method.getParameterTypes()[0];
                    EventHandlerMethod handlerMethod = new EventHandlerMethod();
                    handlerMethod.method = method;
                    handlerMethod.async = annotation.async();
                    handlerMethod.source = source;
                    handlerMethod.event = paramClass;
                    methods.add(handlerMethod);
                }
            }
        }

        public void addMatchingMethodHandlers(Object event, List<EventHandlerMethod> list) {
            for (EventHandlerMethod method : methods) {
                if (method.method.getParameterTypes().length == 1) {
                    Class<?> eventType = method.method.getParameterTypes()[0];
                    if (DunkReflection.isAssignableFrom(eventType, event.getClass())) {
                        list.add(method);
                    }
                }
            }
        }

        private class EventHandlerMethod {
            public Class<?> event;
            public Method method;
            public Object source;
            public boolean async = false;
        }
    }
}