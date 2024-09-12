package com.dunkware.trade.service.stream.serverd.server.spring;

import java.lang.annotation.Annotation;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.StandardServletEnvironment;

import jakarta.annotation.PostConstruct;

@Service
public class ReflectionService {

	private static Reflections reflections = null;
	
	  private static final Logger logger = LoggerFactory.getLogger(ReflectionService.class);

	  public static Class<?>[] findAllAnnotatedClassesInPackage(String packageName,
	      Class<? extends Annotation> clazz) {
	    final List<Class<?>> result = new LinkedList<Class<?>>();
	    final ClassPathScanningCandidateComponentProvider provider =
	        new ClassPathScanningCandidateComponentProvider(false, new StandardServletEnvironment());
	    provider.addIncludeFilter(new AnnotationTypeFilter(clazz));
	    for (BeanDefinition beanDefinition : provider.findCandidateComponents(packageName)) {
	      try {
	        result.add(Class.forName(beanDefinition.getBeanClassName()));
	      } catch (ClassNotFoundException e) {
	        logger.warn("Could not resolve class object for bean definition", e);
	      }
	    }
	    return result.toArray(new Class<?>[result.size()]);
	  }
	
	@PostConstruct
	private void init() { 
		
		Thread runner = new Thread() { 
			
			public void run() { 
				reflections = new Reflections("con.dunkware");
				
			}
		};
		runner.start();
		
	}
	
	
	
	public Set<Class<?>> getClassesAnnotedWith(Class<? extends Annotation> annotation) { 
		return reflections.getTypesAnnotatedWith(annotation);
		
	}
	
	
}
