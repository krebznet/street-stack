package com.dunkware.utils.core.json;

import java.util.HashMap;
import java.util.Map;

import com.dunkware.utils.core.http.DunkHttp;

public class DunkJsonHttpTest {
	
	public static void main(String[] args) {
		Map<String,Object> params = new HashMap<>();
		params.put("username", "fuck@fuck.com");
		params.put("password", "fuck");
		
		String url = "http://localhost:8085/v1/api/user/login-in?username=fuck@fuck.com&password=fuck";
		try {
			DunkHttp.performGetRequest("http://localhost:8086/v1/api/user/login-in", params);;
			
			System.out.println("authenticated");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}

}
