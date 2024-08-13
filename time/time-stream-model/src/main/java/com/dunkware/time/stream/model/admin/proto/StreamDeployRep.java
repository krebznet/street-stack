package com.dunkware.time.stream.model.admin.proto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StreamDeployRep {

	private boolean success = false; 
	private String error;  
}
