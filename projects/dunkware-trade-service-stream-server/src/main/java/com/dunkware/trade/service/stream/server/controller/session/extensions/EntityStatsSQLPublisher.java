package com.dunkware.trade.service.stream.server.controller.session.extensions;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.common.util.mysql.pool.MySqlConnectionPool;
import com.dunkware.common.util.time.DunkTime;
import com.dunkware.trade.service.data.common.stats.entity.sql.EntityStatsSQLHelper;
import com.dunkware.trade.service.stream.server.controller.session.StreamSession;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionExtension;
import com.dunkware.trade.service.stream.server.controller.session.StreamSessionNode;
import com.dunkware.trade.service.stream.server.controller.session.anot.AStreamSessionExt;
import com.dunkware.trade.service.stream.server.spring.ConfigService;

@AStreamSessionExt
public class EntityStatsSQLPublisher implements StreamSessionExtension {

	@Autowired
	private ConfigService config;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private StreamSession session;

	private String tableName;

	private MySqlConnectionPool pool;

	public void initTables() throws Exception {
		Connection cn = null;
		try {
			cn = pool.getConnectionFromPool();
			DatabaseMetaData md = cn.getMetaData();
			ResultSet resultSet = md.getTables(null, "PUBLIC", null, new String[] { "TABLE" });
			boolean exists = false;

			while (resultSet.next()) {
				String table = resultSet.getString("TABLE_NAME");
				if (tableName.equals(table)) {
					exists = true;
				}
			}

			if (exists) {
				Statement st = null;
				try {
					st = cn.createStatement();
					st.execute("DROP TABLE " + config.getStatsDbName() + "." + tableName);
					logger.info("Dropped Existing stats table " + tableName);
				} catch (Exception e) {
					throw new Exception("Exception dropping entity stats table " + e.toString());
				} finally {
					if (st != null) {
						st.close();
					}

				}

			}

			Statement st = null;
			try {
				st = cn.createStatement();
				st.execute(EntityStatsSQLHelper.sqlEntityStatsCreateTable(tableName));
				logger.info("Created Entity Stats Table for stream " + tableName);
			} catch (Exception e) {
				throw new Exception("Exception creating entity stats table " + e.toString());
			} finally {
				if (st != null) {
					st.close();
				}

			}

		} catch (Exception e2) {
			throw new Exception("Creating tables exception entity stats " + e2.toString());
		}

	}

	@Override
	public void sessionStarting(StreamSession session) {
		try {
			this.session = session;
			String formatteDate = DunkTime.format(LocalDate.now(), "YYMMDD");
			tableName = session.getStream().getName() + "_" + formatteDate + "_stats";
			pool = new MySqlConnectionPool(config.getStatsDbHost(), config.getStatsDbName(), config.getStatsDbPort(),
					config.getStatsDbUser(), config.getStatsDbPass(), 1);
			try {
				initTables();  // drop if exists 
				logger.info("Initialized Session Entity Stats Table " + tableName);
			} catch (Exception e) {
				logger.error("Exception initializing entity stats table " + e.toString());
			}
		} catch (Exception e) {
			logger.error("Exception creating entitys tats connection pool + " + e.toString());
			;
		}
		
		pool.close();
		
		session.getInput().getProperties().put("entity_stats_table", tableName);
		session.getInput().getProperties().put("entity_stats_host", config.getStatsDbHost());
		session.getInput().getProperties().put("entity_stats_port", String.valueOf(config.getStatsDbPort()));
		session.getInput().getProperties().put("entity_stats_user", config.getStatsDbUser());
		session.getInput().getProperties().put("entity_stats_pass",config.getStatsDbPass());
	}

	@Override
	public void nodeStarting(StreamSessionNode node) {
		// okay this is painful have to do this. 
	}

	@Override
	public void nodeStarted(StreamSessionNode node) {
		// TODO Auto-generated method stub
		StreamSessionExtension.super.nodeStarted(node);
	}

	@Override
	public void nodeStopping(StreamSessionNode node) {
		// TODO Auto-generated method stub
		StreamSessionExtension.super.nodeStopping(node);
	}

	@Override
	public void nodeStopped(StreamSessionNode node) {
		// TODO Auto-generated method stub
		StreamSessionExtension.super.nodeStopped(node);
	}

	@Override
	public void sessionStarted(StreamSession session) {
		// TODO Auto-generated method stub
		StreamSessionExtension.super.sessionStarted(session);
	}

	@Override
	public void sessionStopping(StreamSession session) {
		// TODO Auto-generated method stub
		StreamSessionExtension.super.sessionStopping(session);
	}

	@Override
	public void sessionStopped(StreamSession session) {
		// TODO Auto-generated method stub
		StreamSessionExtension.super.sessionStopped(session);
	}

	@Override
	public void nodeStartException(StreamSessionNode node, String exception) {
		// TODO Auto-generated method stub
		StreamSessionExtension.super.nodeStartException(node, exception);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

}
