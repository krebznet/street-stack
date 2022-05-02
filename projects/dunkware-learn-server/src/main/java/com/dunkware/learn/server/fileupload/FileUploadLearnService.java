package com.dunkware.learn.server.fileupload;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
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
