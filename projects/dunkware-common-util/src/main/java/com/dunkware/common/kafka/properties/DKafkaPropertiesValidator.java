package com.dunkware.common.kafka.properties;

import com.dunkware.common.util.properties.DProperties;
import com.dunkware.common.util.properties.DPropertiesException;
import com.dunkware.common.util.properties.DPropertiesValidator;

public class DKafkaPropertiesValidator implements DPropertiesValidator {

	private static String[] REQUIRED_PROPS = new String[]{DKafkaProperties.BOOTSTRAP_SERVERS};
	
	public static DKafkaPropertiesValidator newInstance() { 
		return new DKafkaPropertiesValidator();
	}
	
	@Override
	public void validate(DProperties props) throws DPropertiesException {
		for (String string : REQUIRED_PROPS) {
			if(!props.hasProperty(string)) { 
				throw new DPropertiesException("Property " + string + " Missing");
			}
		}
	}

	
}
// DPropertiesRequiredValidator
