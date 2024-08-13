package com.dunkware.time.script.mod.repo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Entity(name = "scriptRelease")
public class DBScriptRepoRelease {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uuid; 
	
	@Column(name = "scriptName")
	private String scriptName;
	
	@Column(name = "timestamp")
	private LocalDateTime timestamp;

	@Column(name = "version")
	private String version;
	
	@Column(columnDefinition = "text",name = "source")
	private String source;
	
	@Column(columnDefinition = "text", name = "metadata")
	private String metadata;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private DBScriptRepo repo; 
	
	
	
	
}
