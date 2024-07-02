package com.dunkware.time.script.mod.repo.persist;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "script_repo_version")
public class ScriptVersionEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uuid;
	
	private double version;
	
	private LocalDateTime timestamp;
	
	@Column(columnDefinition = "text")
	private String script;
	
	@Column(columnDefinition = "text")
	private String updates; 
	
	private List<ScriptSignalEntity> versionSignals; 
	
	private List<ScriptVariableEntity> versionVariables;

}
