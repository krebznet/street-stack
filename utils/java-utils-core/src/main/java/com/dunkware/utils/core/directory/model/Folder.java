package com.dunkware.utils.core.directory.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;


@Data
public class Folder extends Resource {

	private List<Resource> resources = new ArrayList<Resource>();
	
	
}
