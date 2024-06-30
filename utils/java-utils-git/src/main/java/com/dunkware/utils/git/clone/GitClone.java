package com.dunkware.utils.git.clone;

import java.io.File;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import com.dunkware.utils.git.model.GitSource;
import com.dunkware.utils.git.model.GitSource.RepoType;

public class GitClone {

	public static void main(String[] args) {

		GitSource source = new GitSource();
		source.setAccessToken("ATCTT3xFfGN0996ZVITkOEnJDyW7eDjXPbw2b-JYVUUiVOPl4Q9KMsE_Q_XLkTDVq58GvLIkUTw4wsT8NQCjNVESBDzZZDbC--lElbMTIVFfglibnkA85KYbhMHgaYWi9zqjyWM7NfLdxAW6vabY_NASQAhdoiu_MH04SPU1s14pjZSCheco5qk=716F3932\n"
				+ ")");
		source.setUsername("dunkyfunky");
		source.setBranch("main");
		source.setRepoUrl("https://dunkfunky@bitbucket.org/dunkware/street-script.git");

		try {
			clone(source,"/Users/duncankrebs/clone");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
				
	}

	/**
	 * String repoUrl = "https://bitbucket.org/username/repository.git"; String
	 * token = "your-access-token"; // Your token String username = "your-username";
	 * // The username associat
	 * 
	 * @param source
	 * @param directory
	 * @throws Exception
	 */
	public static void clone(GitSource source, String directory) throws Exception {

		File file = null;
		try {
			 file = new File(directory);
			
		} catch (Exception e) {
			throw new Exception("Exception creting clone directory " + directory + " " + e.toString());
		}
		
		if(source.getType() == RepoType.BitBucket) { 
			try {
				Git.cloneRepository().setURI(source.getRepoUrl()).setBranch(source.getBranch()).setDirectory(file)
						.setCredentialsProvider(new UsernamePasswordCredentialsProvider(source.getUsername(), source.getAccessToken())) 
						.call();
			} catch (Exception e) {
				throw new Exception("Exception clonding " + source.toString() + " in directory " + directory + " " + e.toString());
			}	
			
		}
		
		
		

	}
}
