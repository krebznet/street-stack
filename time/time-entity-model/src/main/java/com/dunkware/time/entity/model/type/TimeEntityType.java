package com.dunkware.time.entity.model.type;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor()
@NoArgsConstructor()
public class TimeEntityType {

	private String name; 
	private List<TimeEntityAttributeType> attributes;
	private int version;
}
