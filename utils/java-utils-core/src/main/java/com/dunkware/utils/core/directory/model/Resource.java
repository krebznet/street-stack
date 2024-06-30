package com.dunkware.utils.core.directory.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;

/**
 * Can be a File or Folder 
 */
@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public abstract class Resource {

	private String name; 
	
}
