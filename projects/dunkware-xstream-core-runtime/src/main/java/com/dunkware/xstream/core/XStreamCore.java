package com.dunkware.xstream.core;

import java.io.StringWriter;

import org.requirementsascode.moonwlker.MoonwlkerModule;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamException;
import com.dunkware.xstream.api.XStreamInput;
import com.dunkware.xstream.api.XStreamRegistry;
import com.dunkware.xstream.xproject.bundle.XscriptBundleHelper;
import com.dunkware.xstream.xproject.model.XStreamBundle;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Good old fashion Java Singleton that creates a XStreamRegistry
 * and provides various helper methods for XStream Core Runtime
 * @author dkrebs
 *
 */
public class XStreamCore {
	
	private static XStreamRegistryImpl registry = null;
	private static ObjectMapper bundleMapper = null;

	private XStreamCore() { 
		
	}
	

	public static XStream createStream() { 
		return new XStreamImpl();
	}
	
	public static XStreamInput createInput(XStreamBundle bundle, DExecutor executor) throws XStreamException { 
		if(bundle.getScriptBundle() == null) { 
			throw new XStreamException("XScript Bundle is not set on xstream bundle");
		}
		if(bundle.getTimeZone() == null) {
			
		}
		XStreamInput input = new XStreamInput();
		input.setDate(bundle.getDate());
		input.setExecutor(executor);
		input.setRegistry(getRegistry());
		input.setExtensions(bundle.getExtensions());
		input.setTimeZone(bundle.getTimeZone());
		
		try {
			input.setScript(XscriptBundleHelper.loadProject(bundle.getScriptBundle()));	
		} catch (Exception e) {
			throw new XStreamException("XScript Project Factory Exception " + e.toString());
		}
		return input;
		
	}

	
	public static XStreamBundle deserializeBundle(String json) throws XStreamException { 
		ObjectMapper mapper = buildBundleMapper();
		try {
			XStreamBundle bundle = mapper.readValue(json, XStreamBundle.class);	
			return bundle;
		} catch (Exception e) {
			throw new XStreamException("Exception desirailize XStreamBundle JsonMapping Exception " + e.toString(),e);
		}
	}
	
	
	public static String serializeBundle(XStreamBundle bundle) throws XStreamException { 
		ObjectMapper mapper = buildBundleMapper();
		StringWriter writer = new StringWriter();
		try {
			mapper.writeValue(writer, bundle);
				
		} catch (Exception e) {
			throw new XStreamException("JsonMapping Exception serialize bundle " + e.toString(),e);
		}
		String results = writer.toString();
		return results;
		
	}
	
	public static XStreamRegistry getRegistry() throws XStreamException { 
		if(registry == null) { 
			registry = new XStreamRegistryImpl();
		}
		return registry;
	}
	
	
	private static ObjectMapper buildBundleMapper() throws XStreamException { 
		ObjectMapper objectMapper = new ObjectMapper();
		XStreamRegistryImpl reg = (XStreamRegistryImpl)getRegistry();
		Class<?>[] classes = reg.getExtensionTypeClasses();
				
		// okay cool we need to get the classes
		MoonwlkerModule module =
		   MoonwlkerModule.builder()
		     .fromProperty("with").toSubclassesOf(classes)
		     .build();
		
		 objectMapper.registerModule(module);
		 return objectMapper;
	}
	
	

}
