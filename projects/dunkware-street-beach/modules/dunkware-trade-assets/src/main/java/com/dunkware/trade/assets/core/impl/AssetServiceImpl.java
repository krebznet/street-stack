package com.dunkware.trade.assets.core.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.common.util.helpers.DAnotHelper;
import com.dunkware.spring.runtime.services.EventService;
import com.dunkware.trade.assets.core.Asset;
import com.dunkware.trade.assets.core.AssetService;
import com.dunkware.trade.assets.type.AssetTypeAnotation;
import com.dunkware.trade.assets.type.AssetTypeHandler;
import com.dunkware.trade.persistence.asset.AssetEntity;
import com.dunkware.trade.persistence.services.TradeDataService;

@Service
public class AssetServiceImpl implements AssetService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("TradeAssets");
	
	
	@Autowired
	private TradeDataService dataService; 
	
	@Autowired
	private EventService eventServive; 
	
	@Autowired
	private com.dunkware.spring.runtime.services.ExecutorService executorService;
	
	@Autowired
	private ApplicationContext ac;
	
	private List<Asset> assets = new ArrayList<Asset>();
	private Semaphore assetLock = new Semaphore(1);
	private Map<String,AssetTypeHandler> typeHandlers = new ConcurrentHashMap<String,AssetTypeHandler>();
	private DEventNode eventNode;
	
	@PostConstruct
	private void init() { 
		try {
			this.eventNode = eventServive.getEventRoot().createChild(this);
			Set<Class<?>> clazzes = DAnotHelper.getClassesAnnotedWith(AssetTypeAnotation.class);
			for (Iterator iterator = clazzes.iterator(); iterator.hasNext();) {
				Class<?> class1 = (Class<?>) iterator.next();
				AssetTypeAnotation anot = class1.getAnnotation(AssetTypeAnotation.class);
				try {
					AssetTypeHandler handler = (AssetTypeHandler)class1.newInstance();
					ac.getAutowireCapableBeanFactory().autowireBean(handler);
					if(logger.isDebugEnabled()) { 
						logger.debug(marker, "Added Asset Type Handler {} ", handler.getClass().getName());
					}
					typeHandlers.put(anot.type(), handler);
				} catch (Exception e) {
					logger.error(marker, "Asset Type Handler Fucked " + e.toString());
				}
			}
			
		} catch (Exception e) {
			logger.error(marker, "Outer init exception " + e.toString());;
		} 
	}

	@Override
	public Asset insertAsset(String json, String type) throws Exception {
		AssetTypeHandler handler = getTypeHandler(type);
		handler.validateModel(type);;
		AssetImmpl asset = new AssetImmpl();
		AssetEntity ent = new AssetEntity();
		ent.setCreated(LocalDateTime.now());
		ent.setJson(json);
		ent.setType(type);
		try {
			dataService.persist(ent);
			asset.init(ent, null, false);
		} catch (Exception e) {
			logger.error(marker, "Exception inserting asset " + e.toString());;
		}
		try {
			assetLock.acquire();
			assets.add(asset);
			assetLock.release();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return asset;
		
	}
	
	

	@Override
	public DEventNode getEventNode() {
		return eventNode;
	}

	@Override
	public AssetTypeHandler getTypeHandler(String type) throws Exception {
		AssetTypeHandler h = typeHandlers.get(type);
		if(h == null) 
			throw new Exception("Asset type handler not found for " + type);
		return h;
	}

	@Override
	public Asset getAsset(long id) throws Exception {
		return null; 
	}

	@Override
	public List<Asset> getAssets(String... types) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Asset> getAssets() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
