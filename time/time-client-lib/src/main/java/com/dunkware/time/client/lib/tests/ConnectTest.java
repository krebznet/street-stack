package com.dunkware.time.client.lib.tests;

import com.dunkware.time.client.lib.TimeClient;
import com.dunkware.time.client.lib.TimeConstants;

public class ConnectTest {

	
	public static void main(String[] args) {
		try {
			TimeClient client = TimeClient.connect(TimeConstants.ENV_LOCAL, TimeConstants.CREDS_LOCAL_USERNAME, TimeConstants.CREDS_LOCAL_PASSWORD);
			System.out.println("success");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
