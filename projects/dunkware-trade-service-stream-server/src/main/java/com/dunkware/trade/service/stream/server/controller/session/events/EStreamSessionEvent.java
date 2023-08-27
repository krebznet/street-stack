package com.dunkware.trade.service.stream.server.controller.session.events;

import java.lang.reflect.Method;

import com.dunkware.common.util.events.DEvent;
import com.dunkware.common.util.helpers.DReflectionHelper;
import com.dunkware.trade.service.stream.server.controller.session.StreamSession;
import com.dunkware.trade.service.stream.server.controller.session.core.StreamSessionImpl;

public class EStreamSessionEvent extends DEvent {

	private StreamSession session;
	
	public EStreamSessionEvent(StreamSession session) { 
		this.session = session;
	}

	public StreamSession getSession() {
		return session;
	}

	
	public static DEvent tryMe() { 
		StreamSessionImpl impl = new StreamSessionImpl();
		return new EStreamSessionStarted(impl);
	}
	
	public static void main(String[] args) {
		try {
			StreamSessionImpl impl = new StreamSessionImpl();
			DEvent event = new EStreamSessionStarted(impl);
			Method method = Fuck.class.getMethods()[0];
			Class clazz = method.getParameterTypes()[0];
			if(DReflectionHelper.isAssignableFrom(clazz, event.getClass())) {
				System.out.println("this works too");
			}
			if(clazz.isAssignableFrom(tryMe().getClass())) { 
				System.out.println("fuck here please? ");
			}	else { 
				System.out.println("no");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
		
		
		
	
	}
	
	public static class Fuck { 
		
		public void event(EStreamSessionStarted event) { 
			
		}
	}
	
	
}
