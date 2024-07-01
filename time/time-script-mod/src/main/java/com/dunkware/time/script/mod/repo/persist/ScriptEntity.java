package com.dunkware.time.script.mod.repo.persist;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "script_repo_repository")
public class ScriptEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uuid;
	
	private double version;
	
	private LocalDateTime created;
	
	private LocalDateTime updated; 
	
	private String name; 
	
	private String type; 
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval =  true)
	@JoinColumn(name = "script")
	private List<ScriptVersionEntity> versions = new ArrayList<ScriptVersionEntity>();
	
	
	
	
}