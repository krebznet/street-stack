package com.dunkware.xstream.data.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

import com.dunkware.xstream.data.cache.anot.ACacheExtension;

public class CacheRegistry {
	
	private static CacheRegistry registry = null;
	
	public static CacheRegistry get() throws CacheException { 
		if(registry == null) { 
			registry = new CacheRegistry();
		}
		return registry; 
	}
	
	private List<RegistryExtension>	extensions = new ArrayList<RegistryExtension>();
	
	private CacheRegistry() throws CacheException{ 
		try {
			Reflections reflections = new Reflections("com.dunkware");
			Set<Class<?>> extClasses = reflections.getTypesAnnotatedWith(ACacheExtension.class);
			for (Class<?> expclass : extClasses) {
				ACacheExtension[] expAnots = expclass.getAnnotationsByType(ACacheExtension.class);
				RegistryExtension ext = new RegistryExtension(expclass,expAnots[0].type());
				extensions.add(ext);
				
			}
		} catch (Exception e) {
			throw new CacheException("Exception Creating Registry " + e.toString());
		}
	}
	
	
	public CacheExtension crateExtension(CacheExtensionType type) throws CacheException { 
		for (RegistryExtension registryExtension : extensions) {
			if(registryExtension.isType(type)) { 
				return registryExtension.createExtension();
			}
		}
		throw new CacheException("Extension not found in registry for type " + type.getClass().getName());
	}
	
	
	private static class RegistryExtension { 
		
		private Class<?> type; 
		private Class<?> ext; 
		
		public RegistryExtension(Class<?> ext, Class<?> type) {
			this.ext = ext;
			this.type = type;
		}
		
		public boolean isType(CacheExtensionType ext) { 
			if(type.isInstance(ext)) { 
				return true;
			}
			return false;
		}
		
		public CacheExtension createExtension() throws CacheException { 
			try {
				Object obj = ext.newInstance();
				return (CacheExtension)obj;
						
			} catch (Exception e) {
				throw new CacheException("Exception creating extension instance " + ext.getClass().getName() + " " + e.toString());
			}
		}
		
	}
	
	
}
