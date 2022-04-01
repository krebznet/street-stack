package com.dunkware.trade.tick.service.server.test;

import com.dunkware.common.util.mysql.model.MySqlConnection;
import com.dunkware.common.util.mysql.pool.MySqlConnectionPool;

public class MySqlTest {

	public static void main(String[] args) {
		MySqlConnection con = new MySqlConnection();
		con.setFuzz("123456");
		con.setHost("devfish");
		con.setSchema("dt1-service-tick");
		con.setName("name");
		con.setPort(32060);
		con.setUser("root");
		
		try {
			MySqlConnectionPool pool = new MySqlConnectionPool(con, 1);
			System.out.println("connected");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}
}
