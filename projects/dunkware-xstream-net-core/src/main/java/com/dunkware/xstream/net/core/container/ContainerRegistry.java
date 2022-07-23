package com.dunkware.xstream.net.core.container;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

import com.dunkware.xstream.container.ContainerExtType;
import com.dunkware.xstream.net.core.container.anot.ACacheExtension;

public class ContainerRegistry {
	
	private static ContainerRegistry registry = null;
	
	public static ContainerRegistry get() throws ContainerException { 
		if(registry == null) { 
			registry = new ContainerRegistry();
		}
		return registry; 
	}
	
	private List<RegistryExtension>	extensions = new ArrayList<RegistryExtension>();
	
	private ContainerRegistry() throws ContainerException{ 
		try {
			Reflections reflections = new Reflections("com.dunkware");
			Set<Class<?>> extClasses = reflections.getTypesAnnotatedWith(ACacheExtension.class);
			for (Class<?> expclass : extClasses) {
				ACacheExtension[] expAnots = expclass.getAnnotationsByType(ACacheExtension.class);
				RegistryExtension ext = new RegistryExtension(expclass,expAnots[0].type());
				extensions.add(ext);
				
			}
		} catch (Exception e) {
			throw new ContainerException("Exception Creating Registry " + e.toString());
		}
	}
	
	
	public ContainerExtension create(ContainerExtType type) throws ContainerException { 
		for (RegistryExtension registryExtension : extensions) {
			if(registryExtension.isType(type)) { 
				return registryExtension.createExtension();
			}
		}
		throw new ContainerException("Extension not found in registry for type " + type.getClass().getName());
	}
	
	
	private static class RegistryExtension { 
		
		private Class<?> type; 
		private Class<?> ext; 
		
		public RegistryExtension(Class<?> ext, Class<?> type) {
			this.ext = ext;
			this.type = type;
		}
		
		public boolean isType(ContainerExtType ext) { 
			if(type.isInstance(ext)) { 
				return true;
			}
			return false;
		}
		
		public ContainerExtension createExtension() throws ContainerException { 
			try {
				Object obj = ext.newInstance();
				return (ContainerExtension)obj;
						
			} catch (Exception e) {
				throw new ContainerException("Exception creating extension instance " + ext.getClass().getName() + " " + e.toString());
			}
		}
		
	}
	
	
}
