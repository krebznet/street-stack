package com.dunkware.trade.service.stream.server.store;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.dunkware.common.util.mysql.model.MySqlConnection;
import com.dunkware.trade.service.stream.server.store.repository.StreamStoreDO;
import com.dunkware.trade.service.stream.server.store.repository.StreamStoreRepo;

@Component()
@Profile("StreamStore")
public class StreamStoreService {
	
	@Autowired
	private StreamStoreRepo repo; 
	
	private List<StreamStore> stores = new ArrayList<StreamStore>();
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@PostConstruct
	private void load() { 
		try {
			logger.info("Starting Stream Store Service");
			Iterable<StreamStoreDO> ents  = null;
			try {
			ents = repo.findAll();
			} catch (Exception e) {
				logger.error("Exception querying all stores on load " + e.toString(),e);
				System.exit(-1);
			}
		
			for (StreamStoreDO ent : ents) {
				try {
					StreamStore store = new StreamStore(ent);
					if(logger.isDebugEnabled()) { 
						logger.debug("Loading Store " +ent.getName());
					}
					this.stores.add(store);
				} catch (Exception e) {
					logger.error("Exception loading Stream Stores - Bad Database Connection String ?" + e.toString());
					System.exit(-1);
				}
			}
		} catch (Exception e) {
			logger.error("Load exception " + e.toString());
			System.exit(-1);
		}
	}
	
	public StreamStore createStore(MySqlConnection con) throws Exception { 
		StreamStoreDO storeDO = new StreamStoreDO();
		storeDO.setHost(con.getHost());
		storeDO.setPass(con.getFuzz());
		storeDO.setDbSchema(con.getSchema());
		storeDO.setPort(con.getPort());
		storeDO.setUser(con.getUser());
		storeDO.setName(con.getName());
		StreamStore store = null;
		
		try {
			store = new StreamStore(storeDO);
			repo.save(storeDO);
			stores.add(store);
		} catch (Exception e) {
			throw new Exception("Connection Exception " + e.toString());
		}
		return store; 
	}
	
	public StreamStore getDefaultStore() throws Exception { 
		if(stores.size() == 0) { 
			throw new Exception("No Stream Stores In System!");
		}
		return stores.get(0);
	}
	
	public List<StreamStore> getStores() { 
		return stores;
	}
	
	public List<StreamStore> getActiveStores() { 
		List<StreamStore> results = new ArrayList<StreamStore>();
		for (StreamStore streamStore : results) {
			if(streamStore.isActive()) { 
				results.add(streamStore);
			}
		}
		return results;
	}

}
