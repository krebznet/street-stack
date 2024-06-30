package com.dunkware.utils.git.clone;

import java.io.File;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.UnsupportedCredentialItem;
import org.eclipse.jgit.transport.CredentialItem;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class GitHubClone {

	
	   public static void main(String[] args) {
		   
	        String repoUrl = "https://krebznet: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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@github.com/krebsnet/street-stream-equity.git";
	       repoUrl = "https://krebznet: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@github.com/krebznet/street-stream-equity.git";
	        String branch = "main";
	        String localPath = "/tmp/test";

	        try {
	            Git.cloneRepository()
	                .setURI(repoUrl)
	                .setBranch(branch)
	                .setDirectory(new File(localPath))
	               // .setCredentialsProvider(new UsernamePasswordCredentialsProvider("krebznet", "2goldKids1!"))
	               
	                .call();

	            System.out.println("Repository cloned successfully");
	        } catch (GitAPIException e) {
	            e.printStackTrace();
	            System.out.println("Error cloning repository: " + e.getMessage());
	        }
	    }
}
