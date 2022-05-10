package com.dunkware.trade.tick.api.provider;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.reflections.Reflections;

import com.dunkware.trade.tick.model.provider.TickProviderSpec;

public class TickProviderFactory {

	private static ConcurrentHashMap<String, Class<?>> providers = new ConcurrentHashMap<String, Class<?>>();

	private static boolean initialized = false;

	private static void init() {
		if (initialized) {
			return;
		}
		
		Reflections reflections = new Reflections("com.dunkware.trade");
		
		Set<Class<?>> classes = reflections.getTypesAnnotatedWith(ATickProvider.class);
		for (Class<?> provider : classes) {
			ATickProvider anot = provider.getDeclaredAnnotation(ATickProvider.class);
			providers.put(anot.type(), provider);
		}
		initialized = true;
	}

	public static TickProvider createProvider(TickProviderSpec spec) throws TickProviderException {
		init();
		Class<?> instanceClass = providers.get(spec.getType());
		if(instanceClass == null) { 
			throw new TickProviderException("Tick Provider annotation not found for type " + spec.getType());
		}
		try {
			TickProvider provider = (TickProvider)instanceClass.newInstance();
			return provider;
		} catch (Exception e) {
			throw new TickProviderException("Exception instantiating tick provider " + e.toString());
		}
		
	}

}

