package com.dunkware.time.stream.mod.entity;

import java.time.LocalDateTime;

import com.dunkware.time.script.mod.repo.entity.DBScriptRepo;

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
public class SessionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uuid; 
	
	@Column(name = "stream_name")
	private String streamName;
	
	@Column(name = "start_timestamp")
	private LocalDateTime startTimestamp;

	@Column(name = "stop_timestamp")
	private LocalDateTime stopTimestramp;

	@Column(name = "script_version")
	private String scriptVersion;

	@ManyToOne()
	private StreamEntity stream; 
	
	
	
	
}
