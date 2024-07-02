package com.dunkware.time.script.mod.repo.persist;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

@Entity(name = "script_repo_lease")
public class ScriptLeaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uuid; 
	
	private String identifier; 
	private LocalDateTime tiemstamp;
	private String owner; 
	private String summary;
	
	@ManyToOne()
	private ScriptEntity script; 
}
