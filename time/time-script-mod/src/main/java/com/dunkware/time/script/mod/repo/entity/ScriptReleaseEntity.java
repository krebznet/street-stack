package com.dunkware.time.script.mod.repo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
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
@Entity(name = "script_release")
public class ScriptReleaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uuid; 
	
	@Column(name = "script_name")
	private String scriptName;
	
	@Column(name = "release_timestamp")
	private LocalDateTime releaseTimestamp;

	@Column(name = "release_version")
	private String version;
	
	
	@Column(columnDefinition = "text",name = "release_script")
	private String xscript;
	
	@Column(columnDefinition = "text", name = "release_model")
	private String releaseModel;
	
	@ManyToOne()
	private ScriptEntity script; 
	
	
	
	
}
