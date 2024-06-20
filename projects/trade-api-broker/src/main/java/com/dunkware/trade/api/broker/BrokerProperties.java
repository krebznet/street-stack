package com.dunkware.trade.api.broker;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Generic String Key/Value construct for passing in broker properties to a implementing broker
 * keeping the value strings for serialization and non-confusion. Broker implementing class can
 * throw exception if properties are passed in and do not have expected key/values. 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BrokerProperties {
	
	private Map<String,String> properties;
	

}
