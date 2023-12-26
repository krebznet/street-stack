package com.dunkware.trade.service.data.common.reddis;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dunkware.trade.service.data.common.StreamData;
import com.dunkware.xstream.api.XStream;
import com.dunkware.xstream.api.XStreamEntityVar;
import com.dunkware.xstream.model.entity.EntityVarSnapshot;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.exceptions.JedisException;

public class RedisVarSnapshotWriter {
	/*
	 * private BlockingQueue<EntityVarSnapshot> snapshotQueue = new
	 * LinkedBlockingQueue<EntityVarSnapshot>(); private String reddisHost; private
	 * int reddisPort; private String reddisAuth; private AtomicBoolean closed = new
	 * AtomicBoolean(false); private Logger logger =
	 * LoggerFactory.getLogger(getClass()); private Semaphore errorsLock = new
	 * Semaphore(1); private List<String> errors = new ArrayList<String>(); private
	 * AtomicLong writeCount = new AtomicLong(0); private List<SnapshotWriter>
	 * writers = new ArrayList<RedisStreamWriter.SnapshotWriter>(); private
	 * AtomicInteger writerReturnCount = new AtomicInteger(0);
	 * 
	 * public void open(String host, int port, String auth, int writerThreads)
	 * throws Exception { this.reddisAuth = auth; this.reddisPort = port;
	 * this.reddisHost = host; int x = 0; while(x < writerThreads) { try {
	 * SnapshotWriter writer = new SnapshotWriter(); writers.add(writer); } catch
	 * (JedisException e) { throw new
	 * Exception("Jedis Writer Thread connection exception " + e.toString()); } x++;
	 * }
	 * 
	 * for (SnapshotWriter writer : writers) { writer.start(); }
	 * 
	 * }
	 * 
	 * public long getWriteCount() { return writeCount.get(); }
	 * 
	 * public long getPendingCount() { return snapshotQueue.size(); }
	 * 
	 * public boolean hasErrors() { try { errorsLock.acquire(); if(errors.size() >
	 * 0) { return true; } return false; } catch (Exception e) { return false; }
	 * finally { errorsLock.release(); } }
	 * 
	 * public List<String> getErrors() { return errors; }
	 * 
	 * public void write(EntityVarSnapshot snapshot) {
	 * this.snapshotQueue.add(snapshot); }
	 * 
	 * public void write(List<EntityVarSnapshot> snapshots) {
	 * this.snapshotQueue.addAll(snapshots); }
	 * 
	 * public void close() { if(closed.get() == true) { return; } closed.set(true);
	 * 
	 * int count = 0; while(writerReturnCount.get() < writers.size()) { try {
	 * Thread.sleep(500); count++; if(count > 20) {
	 * logger.error("Writer return count did not work after 20 seconds"); return; }
	 * } catch (Exception e) { logger.error(e.toString(),e); return; } } }
	 * 
	 * 
	 * private void validateOrCreateKey(XStreamEntityVar var, XStream stream) {
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * private class SnapshotWriter extends Thread {
	 * 
	 * Jedis jedis = null; Pipeline pipeline = null;
	 * 
	 * public SnapshotWriter() throws JedisException { jedis = new Jedis(reddisHost,
	 * reddisPort); jedis.auth(reddisAuth); jedis.connect(); pipeline =
	 * jedis.pipelined();
	 * 
	 * }
	 * 
	 * public void run() {
	 * 
	 * int pendingSync = 0; while(true) { try { EntityVarSnapshot snapshot =
	 * snapshotQueue.poll(100, TimeUnit.MILLISECONDS); if(snapshot == null) {
	 * if(pendingSync > 0) { try { pipeline.sync();
	 * 
	 * } catch (JedisException e) { try {
	 * logger.error("Exception Writing Jedis Snapshot Writer Sync " + e,e);
	 * errorsLock.acquire(); errors.add(e.toString()); } catch (Exception e2) {
	 * 
	 * } finally { errorsLock.release(); } } writeCount.addAndGet(pendingSync);
	 * pendingSync = 0; } if(closed.get() == true) {
	 * writerReturnCount.incrementAndGet(); return; } continue; }
	 * 
	 * String key = StreamData.snapshotKey(snapshot);
	 * 
	 * pipeline.hmset(key, snapshot.getVars()); pendingSync++;
	 * 
	 * } catch (InterruptedException e) { return; }
	 * 
	 * if(pendingSync > 500) { try { pipeline.sync(); } catch (JedisException e) {
	 * try { errorsLock.acquire(); errors.add(e.toString()); logger.
	 * error("Exception Writing Jedis Snapshot Writer Sync on pending = bulk size "
	 * + e.toString(),e);
	 * 
	 * } catch (Exception e2) { } finally { errorsLock.release(); } } pendingSync =
	 * 0; }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * }
	 */
}
