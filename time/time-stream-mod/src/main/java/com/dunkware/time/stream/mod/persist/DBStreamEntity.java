package com.dunkware.time.stream.mod.persist;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DBStreamEntity {

	private DBStream stream; 
	private String entityIdent; 
	private long entityId; 
	private String entityType; 
	private LocalDateTime timestamp; 
	
}
