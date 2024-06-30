package com.dunkware.utils.core.filesystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataFile {
	
	private String fileExtension; 
	private String fileName; 
	private String fileContent; 
	
	

}
