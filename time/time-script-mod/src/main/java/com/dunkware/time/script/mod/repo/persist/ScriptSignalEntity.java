package com.dunkware.time.script.mod.repo.persist;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "script_repo_signal")
public class ScriptSignalEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uuid;
	
	private boolean active = false; 
	
	private boolean archived = false; 
	
	private LocalDateTime releaseTimestamp; 
	
	private LocalDateTime removeTimestamp; 
	
	private LocalDateTime updateTimestamp; 
	
	private String ident;
	
	private String name; 
	
	private int id;
	
	@ManyToOne
	private ScriptEntity script;
	
	@ManyToOne
	private ScriptVersionEntity releaseVersion; 
	
	@ManyToOne
	private ScriptVersionEntity removeVersion; 
	
	
	
	
}