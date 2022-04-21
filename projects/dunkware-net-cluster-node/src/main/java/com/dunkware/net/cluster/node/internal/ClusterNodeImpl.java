package com.dunkware.net.cluster.node.internal;

import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.net.cluster.json.node.ClusterNodeStats;
import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.net.cluster.node.ClusterNodeType;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ClusterNodeImpl implements ClusterNode {

	private ClusterNodeStats stats;
	
	private ManagedChannel channel;
	 
	public void update(ClusterNodeStats stats) {
		this.stats = stats;
		if(channel == null) { 
			channel = ManagedChannelBuilder.forTarget(stats.getGrpcEndpoint()).build();
		}
	}
	/*
	 * @Override public Object reqResp(String path, Class respType) throws Exception
	 * { try { String respString = DHttpHelper.getURLContent(getEndpoint(path));
	 * Object resp = DJson.getObjectMapper().readValue(respString, respType);;
	 * return resp; } catch (Exception e) { throw new
	 * Exception("Exception getURL content cluster node path " + path + " " +
	 * e.toString()); } }
	 * 
	 * 
	 * 
	 * @Override public String reqResp(String path) throws Exception { try { String
	 * respString = DHttpHelper.getURLContent(getEndpoint(path)); return respString;
	 * } catch (Exception e) { throw new
	 * Exception("Exception getURL content cluster node path " + path + " " +
	 * e.toString()); } }
	 * 
	 * 
	 * @Override public Object postReqResp(String path, Object req, Class respType)
	 * throws Exception { String serialized = DJson.serialize(req); String
	 * respString = null; try { respString =
	 * DHttpHelper.postJson(getEndpoint(path),serialized); if(respString == null) {
	 * throw new Exception("node req resp returned null path " + getEndpoint(path));
	 * } } catch (Exception e) { throw new Exception("Exception Invoking Endpoint "
	 * + path + " " + e.toString()); } try { Object resp =
	 * DJson.getObjectMapper().readValue(respString, respType); return resp; } catch
	 * (Exception e) { throw new Exception("Exception parsing json response " +
	 * e.toString()); } }
	 */
	
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DDateTime getStartTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClusterNodeType getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean offline() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int runningJobCount() {
		return stats.getRunningJobCount();
	}

	@Override
	public ClusterNodeStats getStats() {
		return stats;
	}

	@Override
	public Object jsonPost(String path, Object request, Class response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void jsonPostVoid(String path, Object request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object jsonPost(String path) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public Channel grpcChannel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object jsonGet(String path, Class response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object jsonGet(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void jsonGetVoid(String path) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String httpGet(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String httpPost(String path) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * This will check and monitor grpc channel is not terminated
	 * @author duncankrebs
	 *
	 */
	private class ChannelMonitor extends Thread { 
		
		public void run() { 
			
		}
	}

	

	
}
