package com.dunkware.common.kafka.producer;

import com.dunkware.common.kafka.properties.DKafkaProperties;
import com.dunkware.common.kafka.properties.DKafkaPropertiesValidator;
import com.dunkware.common.util.properties.DProperties;
import com.dunkware.common.util.properties.DPropertiesException;
import com.dunkware.common.util.properties.DPropertiesValidator;

public class DKafkaByteProducerValidator implements DPropertiesValidator {

	private static String[] REQUIRED_PROPS = new String[]{DKafkaProperties.BOOTSTRAP_SERVERS,DKafkaProperties.TOPICS};
	
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
