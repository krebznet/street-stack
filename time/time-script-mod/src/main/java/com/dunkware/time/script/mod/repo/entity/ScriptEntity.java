package com.dunkware.time.script.mod.repo.entity;

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

@Entity(name = "script_entity")
public class ScriptEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uuid;
	
	private String version;
	
	private LocalDateTime created;
	
	private LocalDateTime updated;
	
	private String name; 
	
	private String type;
	
	private boolean active = true; 
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval =  true)
	@JoinColumn(name = "script")
	private List<ScriptReleaseEntity> releases = new ArrayList<ScriptReleaseEntity>();

	
	
}