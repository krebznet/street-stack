package com.dunkware.time.script.mod.repo.persist;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "script_repo_variable")
public class ScriptVariableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uuid;
	
	private double version;
	
	private LocalDateTime timestamp;
	
	private String varIdent;
	
	private String varDataType; 
	
	private String varFormatType;
	
	private String varName; 
	
	private int varId; 
	
	
	
}
