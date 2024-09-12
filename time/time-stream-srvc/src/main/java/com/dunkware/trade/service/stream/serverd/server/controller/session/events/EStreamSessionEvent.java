package com.dunkware.trade.service.stream.serverd.server.controller.session.events;

import java.lang.reflect.Method;

import com.dunkware.trade.service.stream.serverd.controller.session.StreamSession;
import com.dunkware.trade.service.stream.serverd.controller.session.core.StreamSessionImpl;
import com.dunkware.utils.core.events.DunkEvent;
import com.dunkware.utils.core.helpers.DunkReflection;

public class EStreamSessionEvent extends DunkEvent {

	private StreamSession session;
	
	public EStreamSessionEvent(StreamSession session) { 
		this.session = session;
	}

	public StreamSession getSession() {
		return session;
	}

	
	public static DunkEvent tryMe() { 
		StreamSessionImpl impl = new StreamSessionImpl();
		return new EStreamSessionStarted(impl);
	}
	
	public static void main(String[] args) {
		try {
			StreamSessionImpl impl = new StreamSessionImpl();
			DunkEvent event = new EStreamSessionStarted(impl);
			Method method = Fuck.class.getMethods()[0];
			Class clazz = method.getParameterTypes()[0];
			if(DunkReflection.isAssignableFrom(clazz, event.getClass())) {
				System.out.println("this works too");
			}
			if(clazz.isAssignableFrom(tryMe().getClass())) { 
				System.out.println("fuck here please? ");
			}	else { 
				System.out.println("no");
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
		
		
		
	
	}
	
	public static class Fuck { 
		
		public void event(EStreamSessionStarted event) { 
			
		}
	}
	
	
}
