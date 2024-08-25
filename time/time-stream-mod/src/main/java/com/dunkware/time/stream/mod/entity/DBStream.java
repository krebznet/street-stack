package com.dunkware.time.stream.mod.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.time.script.mod.repo.entity.DBScriptRepo;
import com.dunkware.time.script.mod.repo.entity.DBScriptRepoRelease;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "script_entity")
public class DBStream {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uuid;
	
	private String scriptRepo;
	
	@ManyToOne
	private DBScriptRepo script; 
	
	
	private LocalDateTime created;
	
	private LocalDateTime updated;
	
	@Column(columnDefinition = "text")
	private String settings;
	
	private String name; 
	
	private String type;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval =  true)
	@JoinColumn(name = "script")
	private List<DBScriptRepoRelease> releases = new ArrayList<DBScriptRepoRelease>();

	
	
}