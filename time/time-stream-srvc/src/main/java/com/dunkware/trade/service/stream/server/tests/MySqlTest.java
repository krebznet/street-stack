package com.dunkware.trade.service.stream.server.tests;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlTest {
	
	public static void main(String[] args) {

		try {
			
			  Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.4.2:30091/dt1-stream-store-1?serverTimezone=UTC&autoReconnect=true", "dunktrade", "3goldPigs1!");
			 System.out.println(connection.isClosed());
			System.out.println("created connection");
			Thread.sleep(1000);
			connection.close();
		} catch (Exception sqle) {
			System.out.println(sqle.toString());
			sqle.printStackTrace();
			
		}
	}

}
