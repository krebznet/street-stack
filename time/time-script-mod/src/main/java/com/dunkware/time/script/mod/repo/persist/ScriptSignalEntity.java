package com.dunkware.time.script.mod.repo.persist;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "script_repo_signal")
public class ScriptSignalEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uuid;
	
	private double scriptVersion;
	
	private LocalDateTime timestamp;
	
	private String ident;
	
	private String name; 
	
	private int id; 
	
	
	
}