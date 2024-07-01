package com.dunkware.time.script.mod.repo.persist;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "script_repo_version")
public class ScriptVersionEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uuid;
	
	private double version;
	
	private LocalDateTime timestamp;
	
	@Column(columnDefinition = "text")
	private String script;  
	

}
