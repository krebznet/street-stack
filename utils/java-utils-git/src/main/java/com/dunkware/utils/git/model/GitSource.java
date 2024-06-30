package com.dunkware.utils.git.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GitSource {
	
	public static enum RepoType { 
		BitBucket,GitLab,GitHub
	}

	private RepoType type; 
	private String repoUrl; 
	private String accessToken; 
	private String branch ; 
	private String username; 
	
	
	
	
}
