package com.dunkware.common.util.properties.internal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dunkware.common.util.helpers.DNameSpaceHelper;
import com.dunkware.common.util.properties.DProperties;
import com.dunkware.common.util.properties.DProperty;
import com.dunkware.common.util.properties.DPropertiesException;
import com.dunkware.common.util.properties.DPropertyObject;
import com.dunkware.common.util.properties.DPropertyObjectList;

public class DPropertyObjectListBuilder {

	public static DPropertyObjectList create(String namespace, DProperties properties) throws DPropertiesException { 
		DProperty[] namespaceProps = properties.getPropertiesByPrefix(namespace);
		List<DPropertyObject> propObjects = new ArrayList<DPropertyObject>();
		Set<String> names = getPropertyObjectNames(namespaceProps, namespace);
		for (String name : names) {
			DProperty[] nameProps = properties.getPropertiesByPrefix(namespace + "." + name);
			String objectSpace = namespace + "." + name;
			List<DProperty> objectProps = new ArrayList<DProperty>();
			
			for (DProperty property : nameProps) {
				try {
					String parsedProp = DNameSpaceHelper.namespaceAfter(objectSpace, property.getName(), ".");
					DProperty prop = new DProperty(parsedProp, property.getValue());
					objectProps.add(prop);
				} catch (Exception e) {
					throw new DPropertiesException("Exception parsing object property  for object " + name + " " + e.toString());
				}
			}
			
			DPropertyObject obj = new DPropertyObject(namespace,name,new DProperties(objectProps));
			propObjects.add(obj);
		}
		// so now for each name you need to get all properties that begin with that 
		return new DPropertyObjectList(propObjects);
	}
	
	
	private static Set<String> getPropertyObjectNames(DProperty[] properties, String namespace) throws DPropertiesException {
		Set<String> names = new HashSet<String>();
		for (DProperty prop : properties) {
			try {
				String name = DNameSpaceHelper.nextSymbol(namespace, prop.getName(), ".");
				if(!names.contains(name)) { 
					names.add(name);
				}
			} catch (Exception e) {
				throw new DPropertiesException("Exception getting object name from " + prop.getName() + " with subspace " + namespace);
			}
		}
		return names;
	}
}
