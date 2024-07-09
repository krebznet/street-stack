package com.dunkware.street.street.smart.services;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
// spring boot template 
public class LoginService {

	@PostConstruct
	public void init() { 
		System.out.println("here");
	}
	
	public String auth()	 { 
		return "yes";
	}
	
	// method getTradescri
}
