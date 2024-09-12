package com.dunkware.trade.service.stream.serverd.controller.session.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.dunkware.trade.service.stream.serverd.controller.session.StreamSession;
import com.dunkware.trade.service.stream.serverd.controller.session.StreamSessionException;
import com.dunkware.trade.service.stream.serverd.controller.session.StreamSessionExtension;
import com.dunkware.trade.service.stream.serverd.controller.session.StreamSessionService;
import com.dunkware.trade.service.stream.serverd.controller.session.anot.AStreamSessionExt;
import com.dunkware.trade.service.stream.serverd.controller.session.events.EStreamSessionNodeEvent;
import com.dunkware.trade.service.stream.serverd.controller.session.events.EStreamSessionNodeStartExcepton;
import com.dunkware.trade.service.stream.serverd.spring.ReflectionService;
import com.dunkware.utils.core.helpers.DunkAnot;

@Component
public class StreamSessionServiceImpl implements StreamSessionService {

	private Set<Class<?>> extClasses;
	
	@Autowired
	private ApplicationContext ac; 
	
	@Autowired
	private ReflectionService refService; 
	
	@PostConstruct
	void load() { 
		DunkAnot.getDunkwareReflections();
		
		//extClasses = DunkAnot.getClassesAnnotedWith(AStreamSessionExt.class);
	}
	
	@EventListener
	public void wow(EStreamSessionNodeStartExcepton event) { 
		System.out.println("there you go");
	}
	
	public List<StreamSessionExtension> createExtensions() throws StreamSessionException {
		
		Class<?>[] classes = ReflectionService.findAllAnnotatedClassesInPackage("com.dunkware", AStreamSessionExt.class);
		
		extClasses = DunkAnot.getClassesAnnotedWith(AStreamSessionExt.class);
		
		//extClasses = refService.getClassesAnnotedWith(AStreamSessionExt.class);
		List<StreamSessionExtension> extensions = new ArrayList<StreamSessionExtension>();
		for (Class<?> clazz : classes) {
			try {
				StreamSessionExtension ext = (StreamSessionExtension)clazz.newInstance();
				ac.getAutowireCapableBeanFactory().autowireBean(ext);
				extensions.add(ext);
			} catch (Exception e) {
				throw new StreamSessionException("Exception Stream Session New Instance Class " + clazz.getClass().getName() + "  " + e.toString());
			}
		}
		return extensions;
	}

	@Override
	public StreamSession newSession() {
		return new StreamSessionImpl();
	}
	
	
	
	
	
}
