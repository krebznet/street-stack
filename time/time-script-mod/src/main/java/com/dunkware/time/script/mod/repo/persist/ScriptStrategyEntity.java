package com.dunkware.time.script.mod.repo.persist;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "script_repo_strategy")
public class ScriptStrategyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uuid;
	
	private String name; 
	
	private String type; 
	
	private String description; 
	
	@ManyToOne
	private ScriptVersionEntity version;
	
	
}
