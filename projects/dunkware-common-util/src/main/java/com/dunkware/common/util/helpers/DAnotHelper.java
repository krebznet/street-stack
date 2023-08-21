package com.dunkware.common.util.helpers;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

public class DAnotHelper {
	
	private static Reflections reflections = null;
	
	public static Reflections getDunkwareReflections() { 
		if(reflections == null) { 
			 reflections = new Reflections("com.dunkware");
		}
		return reflections;
	}
	
	public static Set<Class<?>> getClassesAnnotedWith(Class<? extends Annotation> annotation) { 
		return reflections.getTypesAnnotatedWith(annotation);
		
	}
	
	public static List<Method> getMethodsAnnotatedWith(final Class<?> type, final Class<? extends Annotation> annotation) {
	    final List<Method> methods = new ArrayList<Method>();
	    Class<?> klass = type;
	    while (klass != Object.class) { // need to traverse a type hierarchy in order to process methods from super types
	        // iterate though the list of methods declared in the class represented by klass variable, and add those annotated with the specified annotation
	        for (final Method method : klass.getDeclaredMethods()) {
	            if (method.isAnnotationPresent(annotation)) {
	                Annotation annotInstance = method.getAnnotation(annotation);
	                // TODO process annotInstance
	                methods.add(method);
	            }
	        }
	        // move to the upper class in the hierarchy in search for more methods
	        klass = klass.getSuperclass();
	    }
	    return methods;
	}

}
