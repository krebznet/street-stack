package com.dunkware.common.util.properties;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class DPropertiesBuilder {
	
	public static DPropertiesBuilder newBuilder() { 
		return new DPropertiesBuilder();
	}
	
	
	private ConcurrentHashMap<String, String> builderProps = new ConcurrentHashMap<String, String>();
	private List<ImportedProperties> importedProps = new ArrayList<ImportedProperties>();
	
	public DPropertiesBuilder addProperty(String name, String value) { 
		builderProps.put(name, value);
		return this;
	}
	
	public DPropertiesBuilder importProperties(DProperties properties, boolean override) { 
		ImportedProperties prpp = new ImportedProperties();
		prpp.override = override;
		prpp.properties = properties;
		importedProps.add(prpp);
		return this;
	}
	
	public DProperties build() { 
		List<DProperty> inputProps = new ArrayList<DProperty>();
		for (ImportedProperties imported : importedProps) {
			if(imported.override) { 
				for (DProperty dProperty : imported.properties.getPropertyList()) {
					if(builderProps.containsKey(dProperty.getName())) { 
						builderProps.remove(dProperty.getName());
						builderProps.put(dProperty.getName(), dProperty.getValue());
					}
					builderProps.put(dProperty.getName(), dProperty.getValue());
				}
			} else { 
				for (DProperty dProperty : imported.properties.getPropertyList()) {
					if(builderProps.containsKey(dProperty.getName()) == false) { 
						builderProps.put(dProperty.getName(), dProperty.getValue());	
					}
					
				}
			}
		}
		for (String string : builderProps.keySet()) {
			DProperty prop = new DProperty(string, builderProps.get(string));
			inputProps.add(prop);
		}
		return new DProperties(inputProps);
		
	}
	
	private class ImportedProperties { 
		
		public boolean override;
		public DProperties properties;
	}

}
