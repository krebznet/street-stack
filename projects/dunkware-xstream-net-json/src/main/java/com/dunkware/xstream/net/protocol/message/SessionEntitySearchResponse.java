package com.dunkware.xstream.net.protocol.message;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.xstream.net.protocol.data.Bean;

public class SessionEntitySearchResponse {
	
	private int id; 
	private int code; 
	private String error; 
	
	private List<Bean> data = new ArrayList<Bean>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public List<Bean> getData() {
		return data;
	}

	public void setData(List<Bean> data) {
		this.data = data;
	}
	
	

}
