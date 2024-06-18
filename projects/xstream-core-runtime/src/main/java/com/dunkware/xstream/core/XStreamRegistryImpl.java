package com.dunkware.xstream.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

import com.dunkware.xstream.api.XObjectElement;
import com.dunkware.xstream.api.XObjectExpression;
import com.dunkware.xstream.api.XStreamException;
import com.dunkware.xstream.api.XStreamExpression;
import com.dunkware.xstream.api.XStreamExtension;
import com.dunkware.xstream.api.XStreamRegistry;
import com.dunkware.xstream.api.XStreamRuntimeException;
import com.dunkware.xstream.api.XStreamService;
import com.dunkware.xstream.core.annotations.AXObjectElement;
import com.dunkware.xstream.core.annotations.AXObjectExpression;
import com.dunkware.xstream.core.annotations.AXStreamExpression;
import com.dunkware.xstream.core.annotations.AXStreamExtension;
import com.dunkware.xstream.core.annotations.AXStreamService;
import com.dunkware.xstream.xScript.ExpressionType;
import com.dunkware.xstream.xScript.XClassElementType;
import com.dunkware.xstream.xScript.XClassType;
import com.dunkware.xstream.xScript.XExpressionType;
import com.dunkware.xstream.xproject.model.XStreamExtensionType;

/**
 * This will use annotations to build registry content.
 * 
 * @author dkrebs
 *
 */
public class XStreamRegistryImpl implements XStreamRegistry {

	private List<RegistryTypeElement> varExpressions = new ArrayList<RegistryTypeElement>();
	private List<RegistryTypeElement> objectExpressions = new ArrayList<RegistryTypeElement>();
	private List<RegistryTypeElement> objectElements = new ArrayList<RegistryTypeElement>();
	
	private List<RegistryTypeElement> extensions = new ArrayList<RegistryTypeElement>();
	
	private List<AXStreamExtension> extensionAnnotations = new ArrayList<AXStreamExtension>();
	private List<Class> services = new ArrayList<Class>();
	
	public XStreamRegistryImpl() throws XStreamException {
		// reflective yahoo .. com.dunkware
			Reflections reflections = new Reflections("com.dunkware");
			Set<Class<?>> expClasses = reflections.getTypesAnnotatedWith(AXStreamExpression.class);
			for (Class<?> expclass : expClasses) {
				AXStreamExpression[] expAnots = expclass.getAnnotationsByType(AXStreamExpression.class);
				RegistryTypeElement exp = new RegistryTypeElement(expclass, expAnots[0].type());
				varExpressions.add(exp);
				
			}
		Set<Class<?>> extClasses = reflections.getTypesAnnotatedWith(AXStreamExtension.class);
		for (Class<?> extClass : extClasses) {
			AXStreamExtension[] extAnots = extClass.getAnnotationsByType(AXStreamExtension.class);
			// add annotation to list used for bundle serialization 
			this.extensionAnnotations.add(extAnots[0]);
			RegistryTypeElement exp = new RegistryTypeElement(extClass, extAnots[0].type());
			extensions.add(exp);
		}
		Set<Class<?>> classes = reflections.getTypesAnnotatedWith(AXObjectExpression.class);
		for (Class<?> clazz : classes) {
			AXObjectExpression expAnot = clazz.getAnnotation(AXObjectExpression.class);
			RegistryTypeElement element = new RegistryTypeElement(clazz, expAnot.type());
			objectExpressions.add(element);
		}
		 classes = reflections.getTypesAnnotatedWith(AXObjectElement.class);
		for (Class<?> clazz : classes) {
			AXObjectElement expAnot = clazz.getAnnotation(AXObjectElement.class);
			RegistryTypeElement element = new RegistryTypeElement(clazz, expAnot.type());
			objectElements.add(element);
		}
		 classes = reflections.getTypesAnnotatedWith(AXStreamService.class);
			for (Class<?> clazz : classes) {
				services.add(clazz);
			}
	}
	
	public Class<?>[] getExtensionTypeClasses() { 
		Class<?>[] classes = new Class<?>[extensionAnnotations.size()];
		int i = 0; 
		while(i < extensionAnnotations.size()) { 
			classes[i] = extensionAnnotations.get(0).getClass();
			i++;
		}
		return classes;
	}

	@Override
	public boolean hasVarExpressionType(ExpressionType type) {
		for (RegistryTypeElement exp : varExpressions) {
			if(exp.getType().equals(type.getClass())) { 
				return true;
			}
		}
		return false; 
	}

	@Override
	public boolean hasExtensionType(XStreamExtensionType type) {
		for (RegistryTypeElement ext : extensions) {
			if(ext.getType().equals(type.getClass())) { 
				return true;
			}
		}
		return false;
	}

	@Override
	public XStreamExpression createVarExpression(ExpressionType type) {
		for (RegistryTypeElement RegistryTypeElement : varExpressions) {
			if(RegistryTypeElement.getType().isInstance(type)) { 
				try {
					XStreamExpression exp = (XStreamExpression)RegistryTypeElement.getElement().newInstance();
					return exp;
				} catch (Exception e) {
					throw new XStreamRuntimeException("Create Expression Type " + type.getClass().getName() + " Exception " + e.toString(),e);
				}
			}
		}
		throw new XStreamRuntimeException("XStream Expression Type not found in registry " + type.getClass().getName());
	}

	@Override
	public XStreamExtension createExtension(XStreamExtensionType type) {
		for (RegistryTypeElement ext : extensions) {
			if(ext.getType().equals(type.getClass())) { 
				try {
					XStreamExtension extension = (XStreamExtension)ext.getElement().newInstance();
					return extension;
				} catch (Exception e) {
					throw new XStreamRuntimeException("Create Extension Type " + type.getClass().getName() + " Exception " + e.toString(),e);
				}
			}
		}
		throw new XStreamRuntimeException("XStream Extension Type not found in registry " + type.getClass().getName());
	}
	
	

	
	@Override
	public void registerExtension(Class<? extends XStreamExtensionType> type, Class<? extends XStreamExtension> ext) {
		RegistryTypeElement regExt = new RegistryTypeElement(ext, type);
		this.extensions.add(regExt);
	}

	

	@Override
	public XObjectElement createObjectElement(XClassElementType type) {
		for (RegistryTypeElement ext : objectElements) {
			if(ext.getType().isInstance(type)) { 
				try {
					XObjectElement extension = (XObjectElement)ext.getElement().newInstance();
					return extension;
				} catch (Exception e) {
					throw new XStreamRuntimeException("Create XObjet Element Type " + type.getClass().getName() + " Exception " + e.toString(),e);
				}
			}
		}
		throw new XStreamRuntimeException("XStream XObject Element Type not found in registry " + type.getClass().getName());
	}

	@Override
	public XObjectExpression createObjectExpression(XExpressionType type) {
		for (RegistryTypeElement ext : objectExpressions) {
			if(ext.getType().isInstance(type)) { 
				try {
					XObjectExpression extension = (XObjectExpression)ext.getElement().newInstance();
					return extension;
				} catch (Exception e) {
					throw new XStreamRuntimeException("Create XObjet Expression Type " + type.getClass().getName() + " Exception " + e.toString(),e);
				}
			}
		}
		throw new XStreamRuntimeException("XStream XObject Expression Type not found in registry " + type.getClass().getName());
	}


	

	@Override
	public List<XStreamService> createServices() {
		List<XStreamService> list = new ArrayList<XStreamService>();
		for (Class clazz : services) {
			try {
				XStreamService service = (XStreamService)clazz.newInstance();
				list.add(service);
			} catch (Exception e) {
				throw new XStreamRuntimeException("XStream Service Class Instantiation exception " + e.toString() + " class " + clazz.getName());
			}
		}
		return list;
	}








	private class RegistryTypeElement {

		private Class<?> element;
		private Class<?> type;

		public RegistryTypeElement(Class<?> element, Class<?> type) {
			this.element = element;
			this.type = type;
		}

		public Class<?> getElement() {
			return element;
		}

		
		public Class<?> getType() {
			return type;
		}

	

	}
	
	

}
