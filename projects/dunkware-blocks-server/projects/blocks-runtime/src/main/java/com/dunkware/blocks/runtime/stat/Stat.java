package com.dunkware.blocks.runtime.stat;

import java.beans.Transient;

import com.dunkware.blocks.model.FormatType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stat {

	private volatile Object value; 
	@JsonIgnore
	private String group;
	private String name; 
	private FormatType formatType; 
	
	@Transient
	public String formattedValue() {
		if(value == null) { 
			return "null";
		}
		return value.toString();
	}
	
	
	
}
