package com.dunkware.time.entity.model.type;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor()
@NoArgsConstructor()
public class EntityTypeDTO {

	private String name; 
	private List<EntityAttributeTypeDTO> attributes;
	private int version;
}
