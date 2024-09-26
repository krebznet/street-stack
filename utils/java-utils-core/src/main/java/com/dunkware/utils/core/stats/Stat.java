package com.dunkware.utils.core.stats;

import java.beans.Transient;

import com.dunkware.utils.core.format.FormatType;
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

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FormatType getFormatType() {
		return formatType;
	}

	public void setFormatType(FormatType formatType) {
		this.formatType = formatType;
	}
	
	
	
}
