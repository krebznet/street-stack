package com.dunkware.time.entity.model.type;

import com.dunkware.utils.core.data.DataType;
import com.dunkware.utils.core.format.FormatType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntityAttributeTypeDTO {
	
	private String name; 
	private String label; 
	private DataType dataType; 
	private FormatType formatType; 
	private int version; 
	

}
