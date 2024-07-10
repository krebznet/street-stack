package com.dunkware.street.stream.model.resource.impl;

import com.dunkware.street.stream.model.resource.ResourceModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TwsConnectionResourceModel extends ResourceModel {

	private String host; 
	private int port; 
	private String name; 
	private int clientId; 
}
