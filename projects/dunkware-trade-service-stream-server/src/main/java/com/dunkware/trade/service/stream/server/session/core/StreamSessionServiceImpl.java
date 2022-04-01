package com.dunkware.trade.service.stream.server.session.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.dunkware.trade.service.stream.server.session.StreamSession;
import com.dunkware.trade.service.stream.server.session.StreamSessionException;
import com.dunkware.trade.service.stream.server.session.StreamSessionExtension;
import com.dunkware.trade.service.stream.server.session.StreamSessionService;
import com.dunkware.trade.service.stream.server.session.anot.AStreamSessionExt;

@Component
public class StreamSessionServiceImpl implements StreamSessionService {

	private Set<Class<?>> extClasses;
	
	@Autowired
	private ApplicationContext ac; 
	
	@PostConstruct
	void load() { 
		Reflections reflections = new Reflections("com.dunkware");
		extClasses = reflections.getTypesAnnotatedWith(AStreamSessionExt.class);
	}
	
	public List<StreamSessionExtension> createExtensions() throws StreamSessionException { 
		List<StreamSessionExtension> extensions = new ArrayList<StreamSessionExtension>();
		for (Class<?> clazz : extClasses) {
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
