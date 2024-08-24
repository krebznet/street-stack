package com.dunkware.time.script.lib.client.test;

import com.dunkware.time.script.lib.client.impl.HttpTimeScriptClient;
import com.dunkware.time.script.model.proto.XScriptDeployRep;
import com.dunkware.xstream.model.script.release.XScriptProblem;

public class TestScriptDeploy {
	
	
	public static void main(String[] args) {
		try {
			HttpTimeScriptClient client = 
			HttpTimeScriptClient.instance("http://localhost:8085", "dunkware", "dunkStreet@2022");
			XScriptDeployRep rep = client.deployScript("fuckyou", "name", "source");
			System.out.println(rep.isError());
			if(rep.isError()) { 
				for (XScriptProblem string : rep.getProblems().getProblems()) {
					System.out.println(string.getMessage());
				}
			} else { 
				System.out.println("no errror");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
	}

}
