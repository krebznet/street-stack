package com.dunkware.time.stream.mod.persist;

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
@Entity(name = "stream_session")
public class DbSession {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uuid; 
	
	@ManyToOne()
	private DBStream stream; 
	
	@Column(name ="script_version")
	private String scriptVersion;
	
	
	
	
	@Column(name = "start")
	private LocalDateTime startTimestamp; 
	
	@Column(name = "stop")
	private LocalDateTime stopTimestamp; 
	
	
	
}
