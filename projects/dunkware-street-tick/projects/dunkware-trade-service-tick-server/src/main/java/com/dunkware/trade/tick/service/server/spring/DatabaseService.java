package com.dunkware.trade.tick.service.server.spring;


import java.sql.Connection;
import java.sql.Statement;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.dunkware.common.util.mysql.pool.MySqlConnectionPool;

@Component
@Profile("DatabaseService")
public class DatabaseService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private MySqlConnectionPool pool;

	@Value("${server.ds.service.schema}")
	private String db;
	@Value("${server.ds.service.host}")
	private String host;
	@Value("${server.ds.service.user}")
	private String user;
	@Value("${server.ds.service.pass}")
	private String pass;
	@Value("${server.ds.service.port}")
	private int port;

	@PostConstruct
	private void load() {
		try {
			pool = new MySqlConnectionPool(host, db, port, user, pass, 1);
			//Connection cn = pool.getConnectionFromPool();
			//Statement st = cn.createStatement();
			
		} catch (Exception e) {
			logger.error("Exception connecting to tick service database " + e.toString());
			System.exit(-1);
		}
	}

	public MySqlConnectionPool getServiceConnectionPool() {
		return pool;
	}

}
