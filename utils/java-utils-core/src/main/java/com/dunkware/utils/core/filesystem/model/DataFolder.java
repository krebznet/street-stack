package com.dunkware.utils.core.filesystem.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataFolder {
	
	private String name; 
	private List<DataFolder> directories = new ArrayList<DataFolder>();
	private List<DataFile> files;

	
	

}
