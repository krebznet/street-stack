package com.dunkware.trade.service.stream.server.cluster.impl;

import java.util.List;
import java.util.Map;

import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.helpers.DHttpHelper;
import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.service.stream.protocol.cluster.protocol.ClusterProto;
import com.dunkware.trade.service.stream.protocol.cluster.protocol.ClusterProto.NodePing;
import com.dunkware.trade.service.stream.protocol.cluster.spec.ClusterNodeStatsSpec;
import com.dunkware.trade.service.stream.protocol.cluster.spec.ClusterNodeStatus;
import com.dunkware.trade.service.stream.server.cluster.ClusterNode;

public class ClusterNodeImpl implements ClusterNode {
	
	private DEventNode eventNode;  
	private ClusterNodeStatsSpec lastPing;
	
	
	public ClusterNodeImpl(ClusterNodeStatsSpec ping) {
		this.lastPing = ping; 
	}
	

	@Override
	public String getEndpoint() {
		return lastPing.getEndpoint();
	}
	

	@Override
	public ClusterNodeStatsSpec getStats() {
		return lastPing;
	}

	@Override
	public String getEndpoint(String path) {
		if(path.startsWith("/") == false) { 
			path = "/" + path;
		}
		return lastPing.getEndpoint() + path	;
	}

	@Override
	public String getId() {
		return lastPing.getId();
	}

	@Override
	public ClusterNodeStatus getStatus() {
		return lastPing.getStatus();
	}

	@Override
	public DEventNode getEventNode() {
		// TODO Auto-generated method stub
		return null;
	}
	

	public String[] getProfiles() {
		return lastPing.getProfiles();
	}

	@Override
	public boolean hasProfile(String profile) {
		for (String item : getProfiles()) {
			if(item.equals(profile)) { 
				return true; 
			}
		}
		return false; 
	}

	@Override
	public boolean hasProfiles(String[] profiles) {
		for (String profile : profiles) {
			if(!hasProfile(profile)) { 
				return false; 
			}
		}
		return true; 
	}

	
	public void setStats(ClusterNodeStatsSpec stats) {
		this.lastPing = stats;
	}
	
	@Override
	public Object reqResp(String path, Class respType) throws Exception {
		try {
			String respString = DHttpHelper.getURLContent(getEndpoint(path));
			Object resp = DJson.getObjectMapper().readValue(respString, respType);;
			return resp;
		} catch (Exception e) {
			throw new Exception("Exception getURL content cluster node path " + path + " " + e.toString());
		}
	}
	
	

	@Override
	public String reqResp(String path) throws Exception {
		try {
			String respString = DHttpHelper.getURLContent(getEndpoint(path));
			return respString;
		} catch (Exception e) {
			throw new Exception("Exception getURL content cluster node path " + path + " " + e.toString());
		}
	}


	@Override
	public Object postReqResp(String path, Object req, Class respType) throws Exception {
		String serialized = DJson.serialize(req);
		String respString = null;
		try {
			respString = DHttpHelper.postJson(getEndpoint(path),serialized);
			if(respString == null) { 
				throw new Exception("node req resp returned null path " + getEndpoint(path));
			}
		} catch (Exception e) {
			throw new Exception("Exception Invoking Endpoint " + path + " " + e.toString());
		}
		try {
			Object resp = DJson.getObjectMapper().readValue(respString, respType);
			return resp;
		} catch (Exception e) {
			throw new Exception("Exception parsing json response " + e.toString());
		}
	}

	
	

	
	
}
