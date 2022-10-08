package com.dunkware.trade.service.beach.server.trade.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.dunkware.common.util.json.DJson;
import com.dunkware.trade.sdk.core.model.system.SystemType;
import com.dunkware.trade.service.beach.server.system.BeachSystem;
import com.dunkware.trade.service.beach.server.trade.BeachAccount;
import com.dunkware.trade.service.beach.server.trade.BeachSession;
import com.dunkware.trade.service.beach.server.trade.entity.BeachSystemDO;

public class BeachSystemImpl implements BeachSystem {

	private BeachAccount account;
	private SystemType systemType;
	private BeachSession session = null;
	//private BeachSystemSpec spec = new BeachSystemSpec();
	private BeachSystemDO systemEntity;
	
	@Autowired
	private ApplicationContext ac;

	/**
	 * this will load the beach system, if exception occurs and cannot load we just
	 * set our status and notify update.
	 * 
	 * @param account
	 * @param entity
	 */
	public void load(BeachAccount account, BeachSystemDO entity) {
		this.account = account;
		this.systemEntity = entity;

	}

	@Override
	public BeachAccount getAccount() {
		return account;
	}

	@Override
	public SystemType getSystemType() {
		return systemType;
	}

	@Override
	public BeachSession getSession() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BeachSession startSession() throws Exception {
		//if (spec.getStatus() == BeachSessionStatus.Active || spec.getStatus() == BeachSessionStatus.Exiting) {
		//	throw new Exception("Cannot start session on systemn ");
		//}
		BeachSessionImpl session = new BeachSessionImpl();
		ac.getAutowireCapableBeanFactory().autowireBean(session);
		// okay creating new session 
		return null;
		
	}

	@Override
	public void saveType(SystemType type) throws Exception {
		String json = null;
		try {
			json = DJson.serialize(type);
			// now we have 
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	

	@Override
	public boolean inSession() {
		if(session == null) { 
			return false;
		}
		return true;
		// session nneds a stat
		
	}

}
