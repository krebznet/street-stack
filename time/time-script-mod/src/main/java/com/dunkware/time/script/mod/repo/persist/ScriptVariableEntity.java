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
@Entity(name = "script_repo_variable")
public class ScriptVariableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uuid;

	
	private LocalDateTime releaseTimestamp;
	
	private LocalDateTime deleteTimestamp; 
	
	private LocalDateTime archivedTimestamp;
	
	@ManyToOne
	private ScriptVersionEntity releaseVersion; 
	
	@ManyToOne
	private ScriptVersionEntity deleteVersion;
	
	
	@ManyToOne
	private ScriptEntity script;
	
	private String ident;
	
	private String dataType; 
	
	private String formatType;
	
	private String group;
	
	private String name; 
	
	private int id; 
	
	private boolean active; 
	
    private boolean archived; 
   
   
    
    
	
	
}
