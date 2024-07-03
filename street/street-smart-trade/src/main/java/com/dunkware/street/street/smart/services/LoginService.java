package com.dunkware.street.street.smart.services;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class LoginService {

	@PostConstruct
	public void init() { 
		System.out.println("here");
	}
	
	public String auth()	 { 
		return "yes";
	}
}
