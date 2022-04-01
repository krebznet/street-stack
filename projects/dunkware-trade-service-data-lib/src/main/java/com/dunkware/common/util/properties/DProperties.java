/**
 * 
 */
package com.dunkware.common.util.properties;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import com.dunkware.common.util.helpers.DStringHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Duncan Krebs
 * @category Jive Software
 * @date Jun 13, 2014
 * @milestone Fusion M2
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DProperties {

	@JsonIgnore
	private ConcurrentHashMap<String, DProperty> properties = new ConcurrentHashMap<String, DProperty>();
	@JsonProperty(value = "elements")
	private List<DProperty> propertyList = new ArrayList<DProperty>();
	
	public static DProperties loadFromFile(File file) throws IOException {
		List<DProperty> props = new ArrayList<DProperty>();
		BufferedReader br = null;
		String line;
		FileInputStream fis = null;

		try {
			fis = new FileInputStream(file);
			br = new BufferedReader(new InputStreamReader(fis));
			while ((line = br.readLine()) != null) {
				if (!isPropertyLine(line)) {
					continue;
				}
				String[] namevalue = DStringHelper.splitStringOnFirstInstanceOfCharacter(line, "=");
				DProperty dProp = new DProperty(namevalue[0], namevalue[1]);
				props.add(dProp);
			}

			return new DProperties(props);
		} catch (IOException e) {
			throw e;
		} finally {
			if (fis != null) {
				fis.close();
			}
			if (br != null) {
				br.close();
			}
		}

	}

	private static boolean isPropertyLine(String line) {
		if (line.contains("#"))
			return false;
		if (!line.contains("="))
			return false;
		if (line.length() < 3) {
			return false;
		}
		return true;

	}

	public DProperties() {

	}

	
	public Properties toJavaProperties() { 
		Properties props = new Properties();
		for (DProperty dProperty : propertyList) {
			props.put(dProperty.getName(),dProperty.getValue());
		}
		return props;
	}
	

	public DProperties(List<DProperty> properties) {
		this.propertyList = properties;
		for (DProperty dProperty : properties) {
			this.properties.put(dProperty.getName(), dProperty);
		}
	}

	public List<DProperty> getPropertyList() {
		return propertyList;
	}

	public DProperty[] getPropertiesByPrefix(String prefix) {
		List<DProperty> list = new ArrayList<DProperty>();
		for (DProperty kProperty : properties.values()) {
			if (kProperty.getName().startsWith(prefix)) {
				list.add(kProperty);
			}
		}
		return list.toArray(new DProperty[list.size()]);
	}
	
	public void put(String key, String value) { 
		DProperty prop = new DProperty(key, value);
		propertyList.add(prop);
		this.properties.put(key, prop);
	}

	public DProperty[] getPropertiesByName(String name) {
		List<DProperty> list = new ArrayList<DProperty>();
		for (DProperty kProperty : properties.values()) {
			if (kProperty.getName().equals(name)) {
				list.add(kProperty);
			}
		}
		return list.toArray(new DProperty[list.size()]);
	}
	
	public void validate(DPropertiesValidator...validators) throws DPropertiesException { 
	for (DPropertiesValidator dPropertiesValidator : validators) {
		dPropertiesValidator.validate(this);
	}
	}

	public boolean hasProperty(String propName) {
		for (DProperty dProperty : propertyList) {
			if(dProperty.getName().equals(propName)) { 
				return true;
			}
			
		}
		return false;
	}

	public static void main(String[] args) {
		DProperty prop = new DProperty("fuck","value");
		List<DProperty> props = new ArrayList<DProperty>();
		props.add(prop);
		DProperties dprops = new DProperties(props);
		try {
			dprops.getString("fuck");
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	public int getInt(String property)   {
		if (properties.containsKey(property) == false) {
			throw new DPropertiesRuntimeException("Property " + property + " not found");
		}
		try {
			return Integer.valueOf(properties.get(property).getValue());
		} catch (Exception e) {
			throw new DPropertiesRuntimeException(
					"Cannot cast property " + property + " value " + properties.get(property).getValue() + " to int");
		}

	}

	public boolean getBoolean(String property) {
		if (properties.containsKey(property) == false) {
			throw new DPropertiesRuntimeException("Property " + property + " not found");
		}
		try {
			return Boolean.valueOf(properties.get(property).getValue());
		} catch (Exception e) {
			throw new DPropertiesRuntimeException("Cannot cast property " + property + " value "
					+ properties.get(property).getValue() + " to boolean");
		}

	}

	public String getString(String name)  { // KPropertyException
		if (properties.containsKey(name) == false) {
			for (DProperty dProperty : propertyList) {
				properties.put(dProperty.getName(), dProperty);
			}
			if(properties.containsKey(name) == false)
				throw new DPropertiesRuntimeException("Property " + name + " not found");
		}
		return properties.get(name).getValue();

	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Properties Size " + properties.values().size());
		builder.append(System.getProperty("line.separator"));
		builder.append("{");
		for (DProperty dProperty : propertyList) {
			builder.append(System.getProperty("line.separator"));
			builder.append(dProperty.getName() + "=" + dProperty.getValue());	
		}
		builder.append(System.getProperty("line.separator"));
		builder.append("}");
		return builder.toString();
	}

}
