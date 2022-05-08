package com.dunkware.learn.server.fileupload;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

@RestController
@Profile("FileMe")
public class FileUploadLearnService {
	
	
	@PostConstruct
	public void load() { 
		System.out.println("Learn Duncan!");
	}
	
	
	@PostMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file) { 
		try {
			
		
			byte[] payload = file.getBytes();
			String req = new String(payload);
			System.out.println("payload receieved");
			return req;
			
		} catch (Exception e) {
			e.toString();
			return e.toString();
		}
		
		
	}
	

}
