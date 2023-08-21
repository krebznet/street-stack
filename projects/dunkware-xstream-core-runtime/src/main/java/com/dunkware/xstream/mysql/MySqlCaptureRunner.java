package com.dunkware.xstream.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.helpers.DConverter;
import com.dunkware.common.util.mysql.pool.MySqlConnectionPool;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntity;
import com.dunkware.xstream.api.XStreamEntityListener;
import com.dunkware.xstream.api.XStreamRowSignal;
import com.dunkware.xstream.api.XStreamRowSnapshot;
import com.dunkware.xstream.xScript.DataType;
import com.dunkware.xstream.xScript.VarType;

/**
 * Keep the capture logic in its own core class instead of the XStream Extension
 * 
 * @author dkrebs
 *
 */
public class MySqlCaptureRunner implements XStreamEntityListener {

	private Logger logger = LoggerFactory.getLogger(getClass());
	// change this to change batch insert size
	private static final int BATCH_INSERT_SIZE = 3000;

	private BlockingQueue<XStreamRowSnapshot> snapshotQueue = new LinkedBlockingQueue<XStreamRowSnapshot>();

	private BlockingQueue<XStreamRowSignal> signalQueue = new LinkedBlockingQueue<XStreamRowSignal>();
	private Phaser snapshotPhaser = new Phaser();

	private MySqlCaptureInput input;
	private XStream stream;

	private SnapshotWriter snapshotWriter;
	private SnapshotProducer snapshotProducer;

	private String signalQuery = null;
	private String insertQuery = null;
	private List<SnapshotVar> snapshotVars = new ArrayList<SnapshotVar>();

	private AtomicLong insertCount = new AtomicLong(0);

	private MySqlConnectionPool connPool;
	
	private SignalWriter signalWriter; 

	public void startCapture(MySqlCaptureInput input, XStream stream) throws Exception {
		if (logger.isTraceEnabled()) {
			logger.trace("Starting MySqlCapture!!!");
		}
		this.stream = stream;
		this.input = input;
		initialize();

		snapshotProducer = new SnapshotProducer();
		stream.getClock().scheduleRunnable(snapshotProducer, 1);

		snapshotWriter = new SnapshotWriter();
		snapshotWriter.start();
		
		stream.addRowListener(this);
		
		signalWriter = new SignalWriter();
		signalWriter.start();
	}

	public Long getInsertCount() {
		return insertCount.get();
	}

	public int getPendingCount() {
		return snapshotPhaser.getUnarrivedParties();
	}

	public void stopCapture() {
		stream.getClock().unscheduleRunnable(snapshotProducer);
		stream.removeRowListener(this);
		signalWriter.dispose();
		snapshotWriter.interrupt();
	}

	/**
	 * Build the SQL to create a table, execute it, build the query param string and
	 * the order of variables that need to be inserted for the query to work.
	 * 
	 * @throws Exception
	 */
	public void initialize() throws Exception {
		String sql = null;
		String signalSql = null;
		try {
			sql = MySqlLove.buildCreateTableSql(input.getTablePrefix() + "_capture", stream.getInput().getScript());
			signalSql = MySqlLove.buildCreateSignalTableSql(input.getTablePrefix() + "_signals",
					stream.getInput().getScript());
		} catch (Exception e) {
			throw new Exception("MySqlCapture Table Create Sql Excep " + e.toString());
		}
		try {
			connPool = new MySqlConnectionPool(input.getHost(), input.getDatabase(), input.getPort(),
					input.getUsername(), input.getPassword(), 5);
		} catch (Exception e) {
			throw new Exception("MySqlCapture Database Connection " + e.toString());
		}
		Connection con = null;
		try {
			con = connPool.getConnectionFromPool();
			Statement st = con.createStatement();
			if (input.isDropTable()) {
				String query = "DROP TABLE IF EXISTS  " + input.getTablePrefix() + "_row";
				st.execute(query);
			}
			st.execute(sql);
			st.close();
			st = con.createStatement();
			if (input.isDropTable()) {
				String query = "DROP TABLE IF EXISTS  " + input.getTablePrefix() + "_sig";
				st.execute(query);
			}
			st.close();
			st = con.createStatement();
			st.execute(signalSql);
		} catch (Exception e) {
			throw new Exception("Create Table Sql Execution Exception " + e.toString());
		} finally {
			if (con != null)
				connPool.returnConnectionToPool(con);
		}
		// now build the snapshot vars to get a query insert order
		for (VarType varType : stream.getInput().getScript().getStreamVars()) {
			SnapshotVar svar = new SnapshotVar();
			svar.name = varType.getName();
			svar.type = varType.getType();
			snapshotVars.add(svar);
		}
		// now build the insert query param
		StringBuilder writer = new StringBuilder();
		// insert into tickdata (ticker, date, time, last, lastSize, askPrice, bidPrice,
		// volume) VALUES (?,?,?,?,?,?,?,?)";
		writer.append("insert into ");
		writer.append(input.getTablePrefix() + "_capture");
		writer.append(" ");
		writer.append("(");
		// meta columns
		writer.append("_row,_time");
		for (SnapshotVar var : snapshotVars) {
			writer.append(",");
			writer.append(var.name);

		}
		writer.append(") ");
		// meta columns
		writer.append(" VALUES (?,?");

		for (SnapshotVar var : snapshotVars) {
			writer.append(",?");
		}
		writer.append(")");
		insertQuery = writer.toString();

		writer = new StringBuilder();
		// insert into tickdata (ticker, date, time, last, lastSize, askPrice, bidPrice,
		// volume) VALUES (?,?,?,?,?,?,?,?)";
		writer.append("insert into ");
		writer.append(input.getTablePrefix() + "_signals");
		writer.append(" ");
		writer.append("(");
		// meta columns
		writer.append("_row,_time,_signal");
		for (SnapshotVar var : snapshotVars) {
			writer.append(",");
			writer.append(var.name);

		}
		writer.append(") ");
		// meta columns
		writer.append(" VALUES (?,?,?");

		for (SnapshotVar var : snapshotVars) {
			writer.append(",?");
		}
		writer.append(")");
		signalQuery = writer.toString();
	}

	@Override
	public void rowSignal(XStreamEntity row, XStreamRowSignal signal) {
		signalQueue.add(signal);
	}

	private class SnapshotVar {

		public String name;
		public DataType type;

		public SnapshotVar() {

		}

	}
	
	private class SignalWriter extends Thread { 
		private Connection cn;
		private PreparedStatement st;
		private volatile boolean inserting = false;
		private boolean dispose = false;
		
		public void run() { 
			try {
				cn = connPool.getConnectionFromPool();
				st = cn.prepareStatement(signalQuery);
				while(!dispose) { 
					Thread.sleep(3000); 
					 insertPendingSignals();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		
		public void dispose() { 
			dispose = true;
			while(inserting) { 
				
			}
			insertPendingSignals();
			try {
				cn.close();
					
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
		private void insertPendingSignals() { 
			List<XStreamRowSignal> signals = new ArrayList<XStreamRowSignal>();
			while(signalQueue.isEmpty() == false) { 
				signals.add(signalQueue.remove());
			}
			try {
				if(signals.size() > 0) { 
					inserting = true;
					for (XStreamRowSignal signal : signals) {
						st.setString(1,signal.getRow().getId());
						DDateTime dt = DDateTime.of(stream.getInput().getDate(), signal.getTime());
						st.setString(2, signal.getTime().toHHmmSS());
						st.setString(3, signal.getSignalType().getName());
						int index = 4;
						for (SnapshotVar var : snapshotVars) {
							Object value = signal.getVars().get(var.name);
							if(value.equals("null")) { 
								value = null;
							}
							if (var.type == DataType.STR) {
								if (value == null) {
									st.setNull(index, Types.VARCHAR);
								} else {
									st.setString(index, value.toString());
								}
							}
							if (var.type == DataType.DUB) {
								if (value == null) {
									st.setNull(index, Types.DOUBLE);
								} else {
									st.setDouble(index, DConverter.toDouble(value));
								}
							}
							if (var.type == DataType.LONG) {
								if (value == null) {
									st.setNull(index, Types.BIGINT);
								} else {
									st.setLong(index, DConverter.toLong(value));
								}
							}
							if (var.type == DataType.INT) {
								if (value == null) {
									st.setNull(index, Types.INTEGER);
								} else {
									st.setInt(index, DConverter.toInteger(value));
								}
							}
							index++;
						}
						st.addBatch();
					}
					st.executeBatch();
				}
			} catch (Exception e) {
				logger.error("Exception Writing Signals " + e.toString());
			} finally { 
				inserting = false;
			}
		
		}
	}

	private class SnapshotWriter extends Thread {

		private volatile int pendingCount = 0;

		private Connection cn;
		private PreparedStatement st;
		
		private LocalTime lastInsert = LocalTime.now();

		// XStreamRowSnapshot
		// Map<Value> that is it..
		public void run() {
			try {
				cn = connPool.getConnectionFromPool();
				st = cn.prepareStatement(insertQuery);
				while (!interrupted()) {
					XStreamRowSnapshot snapshot = snapshotQueue.take();
					st.setString(1, snapshot.getRowId());
					//DDateTime dt = DDateTime.of(stream.getInput().getDate(), snapshot.getTime());

					st.setString(2,snapshot.getTime().toHHmmSS());
					int index = 3;
					for (SnapshotVar var : snapshotVars) {
						Object value = snapshot.getVarMap().get(var.name);
						if (var.type == DataType.STR) {
							if (value == null) {
								st.setNull(index, Types.VARCHAR);
							} else {
								st.setString(index, value.toString());
							}
						}
						if (var.type == DataType.DUB) {
							if (value == null) {
								st.setNull(index, Types.DOUBLE);
							} else {
								st.setDouble(index, DConverter.toDouble(value));
							}
						}
						if (var.type == DataType.LONG) {
							if (value == null) {
								st.setNull(index, Types.BIGINT);
							} else {
								st.setLong(index, DConverter.toLong(value));
							}
						}
						if (var.type == DataType.INT) {
							if (value == null) {
								st.setNull(index, Types.INTEGER);
							} else {
								st.setInt(index, DConverter.toInteger(value));
							}
						}
						index++;
					}
					pendingCount++;
					st.addBatch();

					if (pendingCount == BATCH_INSERT_SIZE) {
						if (logger.isTraceEnabled()) {
							logger.trace("Execu	startte Capture Batch Start Size " + BATCH_INSERT_SIZE + " "
									+ new Date().getTime());
						}
						try {
							st.executeBatch();
							lastInsert = LocalTime.now();
						} catch (Exception e) {
							logger.error("MySqlCapture execute batch exception " + e.toString());
						}

						if (logger.isTraceEnabled()) {
							logger.trace("Execute Capture Batch Complete " + new Date().getTime());
						}
						pendingCount = 0;
					} else { 
						LocalTime now = LocalTime.now();
						if(now.isAfter(lastInsert.plusSeconds(10))) { 
							st.executeBatch();
							lastInsert = LocalTime.now();
							pendingCount = 0;
						}
					}
				}

			} catch (Exception e) {
				if (e instanceof InterruptedException) {
					gracefulExit();
					return;
				}
				logger.error("Snapshot Writer " + e.toString());
			}

		}

		private void gracefulExit() {
			if (pendingCount > 0) {
				try {

					st.executeBatch();
				} catch (Exception e) {
					logger.error("Exception executing batch in writer graceful exit " + e.toString());
				} finally {
					try {
						st.close();
					} catch (Exception e2) {
					}
					connPool.returnConnectionToPool(cn);
				}
			}
		}
	}

	private class SnapshotProducer implements Runnable {

		public void run() {
			if (logger.isTraceEnabled()) {
				// logger.trace("Snapshot Produce Runnable Invoked");
			}
			for (XStreamEntity xStreamRow : stream.getRows()) {
				snapshotQueue.add(xStreamRow.snapshot());
				// snapshotPhaser.register();
			}

		}
	}

}
