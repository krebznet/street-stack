package com.dunkware.spring.cluster.protocol.descriptors;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.dtime.DDateTime;

public class DunkNetNodeDescriptor   {
	
	private DDateTime timestamp; 
	private List<String> profiles = new ArrayList<String>();
	private String id; 
	private DunkNetDescriptors descriptors;
	
	public boolean hasProfile(String profile) { 
		if(profiles.contains(profile)) { 
			return true;
		}
		return false;
	}
	
	public List<String> getProfiles() {
		return profiles;
	}
	public void setProfiles(List<String> profiles) {
		this.profiles = profiles;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(DDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	@Transient
	public boolean profileExists(String profile) { 
		if(profiles.contains(profile)) { 
			return true;
		}
		return false;
	}
	
	
	public boolean profilesExists(String... values) { 
		for (String string : values) {
			if(!profiles.contains(string)) { 
				return false;
			}
		}
		return true;
	}

	public DunkNetDescriptors getDescriptors() {
		return descriptors;
	}

	public void setDescriptors(DunkNetDescriptors descriptors) {
		this.descriptors = descriptors;
	}
	
	
	
	
	
	
	

}
