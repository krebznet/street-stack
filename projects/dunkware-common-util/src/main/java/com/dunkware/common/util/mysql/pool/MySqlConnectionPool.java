package com.dunkware.common.util.mysql.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.mysql.model.MySqlConnection;

/**
 * @author Duncan Krebs
 * @date Jul 21, 2015
 * @category Comcast M8
 */
public class MySqlConnectionPool {
	

	public static boolean DISABLED = true;
	
	private Logger _logger = LoggerFactory.getLogger(getClass());

	String databaseUrl = "jdbc:mysql://localhost:3306/myDatabase";
	String userName = "userName";
	String password = "userPass";
	String dbName = "dbName";
	String dbHost;
	int dbPort = 1;
	int poolSize = 5;

	Vector<Connection> connectionPool = new Vector<Connection>();

	public MySqlConnectionPool(MySqlConnection con, int poolSize) throws Exception { 
		this(con.getHost(),con.getSchema(),con.getPort(), con.getUser(), con.getFuzz(), poolSize);
	}
	public MySqlConnectionPool(String dbHost, String dbName, int dbPort, String userName, String password, int poolSize) throws Exception {
		this.dbHost = dbHost;
		this.dbName = dbName; 
		this.dbPort = dbPort;
		this.poolSize = poolSize;
		this.userName = userName;
		this.password = password;
	//	databaseUrl = "jdbc:mysql://" + dbHost + ":" + String.valueOf(dbPort) + "/" + dbName + "?rewriteBatchedStatements=true";
		databaseUrl = "jdbc:mysql://" + dbHost + ":" + String.valueOf(dbPort) + "/" + dbName  + "?verifyServerCertificate=FALSE&useServerPrepStmts=false&serverTimezone=UTC&useSSL=false&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true&autoReconnect=true";
		
		initialize();
	}

	private void initialize()  throws Exception {
		// Here we can initialize all the information that we need
		initializeConnectionPool();
	}

	private void initializeConnectionPool() throws Exception {
		if(DISABLED) { 
			return;
		}
		while (!checkIfConnectionPoolIsFull()) {
			// Adding new connection instance until the pool is full
			connectionPool.addElement(createNewConnectionForPool());
		}
		
	}

	private synchronized boolean checkIfConnectionPoolIsFull()  {
		// Check if the pool size
		if (connectionPool.size() < poolSize || connectionPool.size() == poolSize) {
			return false;
		}

		return true;
	}
	
	public void close() { 
		for (Object object : this.connectionPool) {
			Connection cn =	(Connection)object;
			try {
				cn.close();				
			} catch (Exception e) {
				// TODO: handle exception
			}

			
		}
	}

	// Creating a connection
	private Connection createNewConnectionForPool() throws Exception {
		Connection connection = null;

		try {
						
			  Class.forName("com.mysql.cj.jdbc.Driver");
			  connection = DriverManager.getConnection(databaseUrl, userName, password);
			  if(_logger.isDebugEnabled()) {
			  _logger.debug("Created New Connection from pool " + databaseUrl + " " +
			  userName); }
			 
		} catch (Exception sqle) {
			throw sqle;
		}
		return connection;
	}

	public synchronized Connection getConnectionFromPool() throws Exception {
		if(DISABLED) { 
			return createNewConnectionForPool();
		}
		Connection connection = null;
		// Check if there is a connection available. There are times when all
		// the connections in the pool may be used up
		if (connectionPool.size() > 0) {
			connection = (Connection) connectionPool.firstElement();
			connectionPool.removeElementAt(0);
			try {
				if(connection.isClosed()) { 
					connection = createNewConnectionForPool();
				}	
				if(connection.isReadOnly()) { 
					connection = createNewConnectionForPool();
				}
				if(connection.isValid(4000) == false) { 
					_logger.error("Connection was found to not be valid, creating new connection");
					connection = createNewConnectionForPool();
				}
			} catch (Exception e) {
				_logger.error("Exception behind the scenes created a new connection from closed one " + e.toString());
			}
			
		}
		// Giving away the connection from the connection pool
		return connection;
	}

	public synchronized void returnConnectionToPool(Connection connection) {
		if(DISABLED) { 
			try {
				connection.close();
			} catch (Exception e) {
			}
		}
		// Adding the connection from the client back to the connection pool
		connectionPool.addElement(connection);
	}
}
