package com.dunkware.trade.service.stream.server.controller.session.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.dunkware.trade.service.stream.server.controller.session.StreamSession;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionException;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionExtension;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionService;
import com.dunkware.trade.service.stream.server.controller.session.anot.AStreamSessionExt;
import com.dunkware.utils.core.helpers.DunkAnot;

@Component
public class StreamSessionServiceImpl implements StreamSessionService {

	private Set<Class<?>> extClasses;
	
	@Autowired
	private ApplicationContext ac; 
	
	@PostConstruct
	void load() { 
		extClasses = DunkAnot.getClassesAnnotedWith(AStreamSessionExt.class);
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
