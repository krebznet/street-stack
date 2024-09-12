package com.dunkware.trade.service.stream.serverd.spring;

import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;


@Component
public class ResourceService {

	@Autowired
	private ResourceLoader resourceLoader; 
	
	@PostConstruct
	private void init() { 
		
	}
	
	
	public File getResourceFile(String path) throws Exception { 
		final Resource resource = resourceLoader.getResource("classpath:" + path);
		return resource.getFile();
		
	}
	
	
	public String getResourceFileString(String path) throws Exception { 
		final Resource resource = resourceLoader.getResource("classpath:" + path);
		if(resource == null) { 
			throw new Exception("Resource Path Not Found " + path);
		}
		Reader reader = new InputStreamReader(resource.getInputStream());
		String filedata = FileCopyUtils.copyToString(reader);
		return filedata;
	}
}
